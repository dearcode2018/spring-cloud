/**
  * @filename ConsumerStarter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

 /**
 * @type ConsumerStarter
 * @description 应用启动器
 * @author qianye.zheng
 */
@SpringBootApplication(scanBasePackages = {"com.hua"})
@EnableDiscoveryClient
@EnableFeignClients
/* 允许容错，标注了之后，@HystrixCommand才能工作 */
@EnableHystrix
public class ConsumerStarter
{
	
	/**
	 * 
	 * @description  启动入口: 主方法
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(ConsumerStarter.class, args);
	}
	
	
}
