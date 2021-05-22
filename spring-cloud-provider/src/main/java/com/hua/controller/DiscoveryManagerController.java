/**
  * @filename DiscoveryManagerController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import javax.annotation.Resource;

import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaServiceRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

/**
 * @type DiscoveryManagerController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/discoveryManager")
public class DiscoveryManagerController extends BaseController {
	
	@Resource
	private EurekaServiceRegistry eurekaServiceRegistry;
	
	@Resource
	private EurekaRegistration eurekaRegistration;
	
	@Resource
	EurekaClient eurekaClient;
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping("/offline")
	public String offline() {
		//discoveryClient.shutdown();
		System.out.println("DiscoveryManagerController.offline()");
		// 注册 - 上线
		//eurekaServiceRegistry.register(eurekaRegistration);
		
		// 取消注册 - 下线
		//eurekaServiceRegistry.deregister(eurekaRegistration);
		
		eurekaClient.shutdown();
		//System.exit(0);
		
		return "111";
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping("/offline2")
	public String offline2() {
		//discoveryClient.shutdown();
		System.out.println("DiscoveryManagerController.offline()");
		// 注册 - 上线
		eurekaServiceRegistry.register(eurekaRegistration);
		
		// 取消注册 - 下线
		eurekaServiceRegistry.deregister(eurekaRegistration);
		//System.exit(0);
		
		return "111";
	}
	
}
