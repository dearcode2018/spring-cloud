/**
  * @filename FeignConfig4Provider2.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.feign.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;

/**
 * @type FeignConfig4Provider2
 * @description 
 * @author qianye.zheng
 */
public class FeignConfig4Provider2
{
	
	/* 超时时间 */
    public static int connectTimeOutMillis = 3000;
    
    /* 超时时间 */
    public static int readTimeOutMillis = 3000;

	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public RequestInterceptor interceptor()
	{
		return new DefaultInterceptor();
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public Logger.Level feignLoggerLevel()
	{
		return Logger.Level.BASIC;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
/*	@Bean
	public Request.Options options()
	{
		return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
	}
	
	/**
	 * 
	 * @description 重试器
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public Retryer retryer()
	{
		return Retryer.NEVER_RETRY;
	}
	
	/**
	 * 
	 * @type DefaultInterceptor
	 * @description 
	 * @author qianye.zheng
	 */
	class DefaultInterceptor implements RequestInterceptor
	{
		/**
		 * @description 
		 * @param template
		 * @author qianye.zheng
		 */
		@Override
		public void apply(RequestTemplate template)
		{
			//template.header("X-APPID", "xx");
		}
	}
	
}
