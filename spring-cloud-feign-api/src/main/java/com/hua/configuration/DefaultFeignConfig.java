/**
  * @filename DefaultFeignConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;

/**
 * @type DefaultFeignConfig
 * @description Feign默认配置
 * @author qianye.zheng
 */
public class DefaultFeignConfig
{

	/* 连接超时时间 */
	@Value("${feign.connect.time-out-millis:1000}")
    public long connectTimeOutMillis;
    
    /* 读取超时时间 */
	@Value("${feign.read.time-out-millis:5000}")
    public long readTimeOutMillis;

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
	@Bean
	public Request.Options options()
	{
		return new Request.Options(connectTimeOutMillis, TimeUnit.MILLISECONDS, 
		        readTimeOutMillis, TimeUnit.MILLISECONDS, false);
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
