/**
  * @filename DefaultFallbackProvider.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import com.netflix.hystrix.exception.HystrixTimeoutException;

/**
 * @type DefaultFallbackProvider
 * @description 
 * @author qianye.zheng
 */
public class DefaultFallbackProvider implements FallbackProvider
{

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public String getRoute()
	{
		
		// 返回null或 * 表示匹配所有路由
		return "*";
		//return "customers";
	}

	/**
	 * @description 
	 * @param route
	 * @param cause
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause)
	{
		
		if (cause instanceof HystrixTimeoutException)
		{
			// 网关超时
			return response(HttpStatus.GATEWAY_TIMEOUT);
		} else
		{
			// 服务器错误
			return response(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @description 
	 * @param status
	 * @return
	 * @author qianye.zheng
	 */
	private ClientHttpResponse response(final HttpStatus status)
	{
		return new ClientHttpResponse()
		{
			
			@Override
			public HttpHeaders getHeaders()
			{
				final HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				
				return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException
			{
				return new ByteArrayInputStream("fallback".getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException
			{
				return status.getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException
			{
				return status;
			}
			
			@Override
			public int getRawStatusCode() throws IOException
			{
				return status.value();
			}
			
			@Override
			public void close()
			{
			}
		};
	}
	
}
