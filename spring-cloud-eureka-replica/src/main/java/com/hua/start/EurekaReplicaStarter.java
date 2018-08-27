/**
  * @filename EurekaReplicaStarter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

 /**
 * @type EurekaReplicaStarter
 * @description 应用启动器
 * @author qianye.zheng
 */
@SpringBootApplication(scanBasePackages = {"com.hua"})
// 声明一个Eureka服务
@EnableEurekaServer
@EnableDiscoveryClient
public class EurekaReplicaStarter
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
		
		SpringApplication.run(EurekaReplicaStarter.class, args);
	}
	
	
}
