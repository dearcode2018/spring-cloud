/**
  * @filename ApplicationStarter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

 /**
 * @type ApplicationStarter
 * @description 应用启动器
 * @author qianye.zheng
 */
/* 导入资源: xml配置文件 */
//@ImportResource({"classpath:conf/xml/spring-config.xml"})
/* @SpringBootApplication已经包含 @Configuration @EnableAutoConfiguration @ComponentScan */
//@SpringBootApplication(scanBasePackages = {"com.hua"})
/* 该类在根包(basePackage)下，则无须再指定scanBasePackages */
@SpringBootApplication
//声明一个Eureka服务
@EnableDiscoveryClient
// OpenFeign
@EnableFeignClients
//@EnableDiscoveryClient
/* 启动指定特性 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@EnableDiscoveryClient
/* 开启断路器 */
@EnableHystrix
//@EnableHystrixDashboard //
//@EntityScan
public class ApplicationStarter
{
	
	
	/**
	 * 
	 * @description  启动入口: 主方法
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(ApplicationStarter.class, args);
	}
	
	
	
	
	
	
}
