/**
  * @filename ZuulStarter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

 /**
 * @type ZuulStarter
 * @description 应用启动器
 * @author qianye.zheng
 */
@SpringBootApplication(scanBasePackages = {"com.hua"})
//@EnableEurekaClient
/* 开启Zuul (网关)代理 */
@EnableZuulProxy
public class ZuulStarter
{
	
	
	/**
	 * 
	 * @description  启动入口: 主方法
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(ZuulStarter.class, args);
	}
	
	
}
