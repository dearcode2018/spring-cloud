/**
  * @filename ApplicationStarter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

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
/* 启动指定特性 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@EnableDiscoveryClient
//@EnableCircuitBreaker
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
