/**
  * @filename ZuulConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryFactory;
import org.springframework.cloud.netflix.zuul.filters.post.LocationRewriteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @type ZuulConfig
 * @description 
 * @author qianye.zheng
 */
//@Configuration
public class ZuulConfig
{
	
	@Bean
	public LoadBalancedRetryFactory retryFactory()
	{
		return new LoadBalancedRetryFactory()
				{
					/**
					 * @description 
					 * @param service
					 * @return
					 * @author qianye.zheng
					 */
					@Override
					public BackOffPolicy createBackOffPolicy(String service)
					{
						// 指数级回退
						return new ExponentialBackOffPolicy();
					}
					
				};
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	public LocationRewriteFilter locationRewriteFilter()
	{
		
		/*
		 * 过滤器作用于Location所有3XX响应代码的标头
		 */
		
		return new LocationRewriteFilter();
	}
	
	/**
	 * 
	 * @description 启用跨域
	 * @return
	 * @author qianye.zheng
	 */
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() {
			/**
			 * @description 
			 * @param registry
			 * @author qianye.zheng
			 */
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				/*
				 * allowedOrigins，allowedMethods，allowedHeaders，exposedHeaders，allowCredentials
				 */
				registry
				// 映射目标结构
				.addMapping("/api/**")
				// 允许的源
				.allowedOrigins("http://a.b.c")
				// 允许的方法
				.allowedMethods("GET", "POST");
			}
			
		};
	}
}
