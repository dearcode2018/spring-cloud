/**
  * @filename DiscoveryManagerController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.DiscoveryClient;

/**
 * @type DiscoveryManagerController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/discoveryManager")
public class DiscoveryManagerController extends BaseController {
	
	@Resource
    private DiscoveryClient discoveryClient;
	
	/**
	 * 
	 * @description 服务下线
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping("/offline")
	public String offline() {
		discoveryClient.shutdown();
		
		return "";
	}
	
}
