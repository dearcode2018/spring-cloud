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
		eurekaServiceRegistry.register(eurekaRegistration);
		
		// 取消注册 - 下线
		//eurekaServiceRegistry.deregister(eurekaRegistration);
		//System.exit(0);
		
		return "111";
	}
	
}
