/**
  * @filename ClientConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.client;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryFactory;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @type ClientConfiguration
 * @description 
 * @author qianye.zheng
 */
@Configuration
public class ClientConfiguration
{

	/**
	 * @LoadBalanced 标注的bean，在引用的时候也需加上@LoadBalanced
	 * 例如: 
	 @Resource
	 @LoadBalanced
	 private RestTemplate restTemplate;
	 * 
	 * 
	 */
	
	
	/* ===========================  RestTemplate为客户端 ========================== */	
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Primary
	@Bean
	public ClientHttpRequestFactory factory()
	{
		final HttpClient httpClient = HttpClientBuilder.create()
				// 连接池最大连接数
				.setMaxConnTotal(1000)
				// 单主机最大连接数
				.setMaxConnPerRoute(200)
				.build();
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		// 连接超时时间 单位: 毫秒
		factory.setConnectTimeout(20 * 1000);
		// 读取超时时间，单位: 毫秒
		factory.setReadTimeout(10 * 1000);
		
		return factory;
	}
	
	/**
	 * 
	 * @description 负载均衡
	 * @param factory
	 * @return
	 * @author qianye.zheng
	 */
	@LoadBalanced
	@Bean
	public RestTemplate loadBalancedRestTemplate(final ClientHttpRequestFactory factory)
	{
		return buildRestTemplate(factory);
	}
	
	/**
	 * 
	 * @description 
	 * @param factory
	 * @return
	 * @author qianye.zheng
	 */
	@Primary
	@Bean
	public RestTemplate restTemplate(final ClientHttpRequestFactory factory)
	{
		return buildRestTemplate(factory);
	}
	
	/**
	 * 
	 * @description 
	 * @param factory
	 * @return
	 * @author qianye.zheng
	 */
	private RestTemplate buildRestTemplate(final ClientHttpRequestFactory factory)
	{
		final RestTemplate restTemplate = new RestTemplate(factory);
		final List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		// 重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
		converters.stream().filter(converter -> StringHttpMessageConverter.class == converter.getClass())
		.findFirst().ifPresent(converters :: remove);;
		// 添加基于UTF8编码的消息转换器
		converters.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		return restTemplate;
	}
	
	
	
	
	
	
	
	
/* ===========================  WebClient为客户端 ========================== */	
	
	@Resource
	private LoadBalancerExchangeFilterFunction lbFunction;
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	@LoadBalanced
	public WebClient loadBalancedWebClient()
	{
		
		return WebClient.builder().filter(lbFunction).build();
	}
	
	
	
	/* ===========================  ========================== */	
	
	
	/**
	 * 
	 * @description 负载均衡重试
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public LoadBalancedRetryFactory retryFactory()
	{
		return new LoadBalancedRetryFactory() {
			/**
			 * @description 
			 * @param service
			 * @return
			 * @author qianye.zheng
			 */
			@Override
			public BackOffPolicy createBackOffPolicy(String service)
			{
				
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
/*	@Bean
	public LoadBalancedRetryListenerFactory retryListenerFactory()
	{
		
	}*/
	
	
	
	
	
	
}
