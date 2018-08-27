/**
  * @filename RestService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @type RestService
 * @description 
 * @author qianye.zheng
 */
@Configuration
public class RestService
{

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate()
	{
		System.out.println("instance RestTemplate");
		
		return new RestTemplate();
	}
	
}
