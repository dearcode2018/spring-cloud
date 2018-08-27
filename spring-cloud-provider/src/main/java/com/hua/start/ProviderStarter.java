/**
  * @filename ProviderStarter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

 /**
 * @type ProviderStarter
 * @description 应用启动器
 * @author qianye.zheng
 */
@SpringBootApplication(scanBasePackages = {"com.hua"})
/*  */
@EnableEurekaClient
public class ProviderStarter
{
	
	
	/**
	 * 
	 * @description  启动入口: 主方法
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(ProviderStarter.class, args);
	}
	
	
}
