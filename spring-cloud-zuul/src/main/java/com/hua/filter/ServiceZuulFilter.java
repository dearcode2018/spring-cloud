/**
  * @filename ServiceZuulFilter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
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
/*
		RequestContext ctx = RequestContext.getCurrentContext();
		return !ctx.containsKey(FORWARD_TO_KEY) // a filter has already forwarded
				&& !ctx.containsKey(SERVICE_ID_KEY); // a filter has already determined serviceId		
*/		
		
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
		RequestContext context = RequestContext.getCurrentContext();
		//context.setResponseStatusCode(404);
		context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
		// context.set("checkAuth", false);
		// 路由到指定url
		context.setRouteHost(null);
		HttpServletRequest request = context.getRequest();
		
		// 拦截了通往微服务的请求
		// 不请求微服务
		//context.setSendZuulResponse(false);
		// 401 无权限
		//context.setResponseStatusCode(401);
		
		
		return context;
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
		
		return FilterConstants.PRE_TYPE;
	}

	/**
	 * @description 执行顺序，数值越小优先级越高
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public int filterOrder()
	{
		
		//return PRE_DECORATION_FILTER_ORDER - 1;
		return 100;
	}

}
