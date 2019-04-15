/**
  * @filename OkHttpRoutingFilter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.HttpMethod;

/**
 * @type OkHttpRoutingFilter
 * @description 
 * @author qianye.zheng
 */
//@Component
public class OkHttpRoutingFilter extends ZuulFilter
{
	
	@Resource
	private ProxyRequestHelper helper;

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean shouldFilter()
	{
		return RequestContext.getCurrentContext().getRouteHost() != null 
				&& RequestContext.getCurrentContext().sendZuulResponse();
	}

	/**
	 * @description 
	 * @return
	 * @throws ZuulException
	 * @author qianye.zheng
	 */
	@Override
	public Object run() throws ZuulException
	{
		OkHttpClient httpClient = new OkHttpClient.Builder().build();
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String method = request.getMethod();
		String uri = helper.buildZuulRequestURI(request);
		Headers.Builder headers = new Headers.Builder();
		Enumeration<String> headerNames = request.getHeaderNames();
		// 设置头部信息
		while (headerNames.hasMoreElements())
		{
			String name = headerNames.nextElement();
			Enumeration<String> values = request.getHeaders(name);
			while (values.hasMoreElements())
			{
				String value = values.nextElement();
				headers.add(name, value);
			}
		}
		
		try
		{
			InputStream inputStream = request.getInputStream();
			RequestBody requestBody = null;
			if (null != inputStream && HttpMethod.permitsRequestBody(method))
			{
				MediaType mediaType = null;
				if (null != headers.get("Content-Type"))
				{
					mediaType = MediaType.parse(headers.get("Content-Type"));
				}
				requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
			}
			Request.Builder builder = new Request.Builder()
					.headers(headers.build())
					.url(uri)
					.method(method, requestBody);
			
			Response response = httpClient.newCall(builder.build()).execute();
			LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
			for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet())
			{
				responseHeaders.put(entry.getKey(), entry.getValue());
			}
			helper.setResponse(response.code(), response.body().byteStream(), responseHeaders);
			// 阻止 SimpleHostRoutingFilter 运行
			context.setRouteHost(null);
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public String filterType()
	{
		return "route";
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public int filterOrder()
	{
		return 1;
	}

}
