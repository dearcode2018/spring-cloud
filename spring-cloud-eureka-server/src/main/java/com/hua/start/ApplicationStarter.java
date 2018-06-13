/**
  * @filename ApplicationStarter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

 /**
 * @type ApplicationStarter
 * @description 应用启动器
 * @author qianye.zheng
 */
/* 开启服务-注册中心 */
@EnableEurekaServer
@SpringBootApplication(scanBasePackages = {"com.hua"})
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
