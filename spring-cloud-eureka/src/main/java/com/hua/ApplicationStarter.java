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
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

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
@EnableEurekaServer
@EnableDiscoveryClient
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
		//args = new String[] {"spring-boot:run Dport=6868 Deureka.server=http://localhost:6868/eureka"};
		//args = new String[] {"spring-boot:run", "-Dport=6868", "-Deureka.server=http://localhost:6868/eureka"};
		//args = new String[] {"spring-boot:run", "-Dport=6869", "-Deureka.server=http://localhost:6869/eureka"};
		
		SpringApplication.run(ApplicationStarter.class, args);
	}
	
	
	
	
	
	
}
