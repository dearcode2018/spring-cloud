/**
  * @filename RouteDeclare.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @type RouteDeclare
 * @description 
 * @author qianye.zheng
 */
@Configuration
public class RouteDeclare {

	
	/**
	 * 
	 * @description 
	 * @param builder
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public RouteLocator myRoutes(final RouteLocatorBuilder builder) {
		return builder.routes().route(p -> p.path("/get").filters(f -> f.addRequestHeader("hello2", "world2"))
				// 转发
				.uri("http://httpbin.org:80"))
				.build();
	}
	
}
