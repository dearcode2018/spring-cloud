/**
  * @filename ServiceZuulFilter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

 /**
 * @type ServiceZuulFilter
 * @description 
 * @author qianye.zheng
 */
@Component
public class ServiceZuulFilter extends ZuulFilter
{

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean shouldFilter()
	{// 需要过滤的
		
		System.out.println("ServiceZuulFilter.shouldFilter()");
		
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
		System.out.println("ServiceZuulFilter.run()");
		 // 业务逻辑
		RequestContext requestContext = RequestContext.getCurrentContext();
		//requestContext.setResponseStatusCode(404);
		
		return requestContext;
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public String filterType()
	{
		// 不能返回null，否则抛空指针异常
		/*
		 * "pre" for pre-routing filtering,
		 * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
		 * We also support a "static" type for static responses see  StaticResponseFilter.
		 */
		
		return "pre";
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public int filterOrder()
	{
		return 0;
	}

}
