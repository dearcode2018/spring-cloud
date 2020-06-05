/**
  * @filename ResponseFilter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.filter;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @type ResponseFilter
 * @description 
 * @author qianye.zheng
 */
public class ResponseFilter extends ZuulFilter
{

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean shouldFilter()
	{
		return true;
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
		/*
		 * 操作响应
		 */
		RequestContext context = RequestContext.getCurrentContext();
    	HttpServletResponse servletResponse = context.getResponse();
    	servletResponse.addHeader("X-Sample", UUID.randomUUID().toString());
    	
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
		return FilterConstants.POST_TYPE;
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public int filterOrder()
	{
		return 10;
	}

}
