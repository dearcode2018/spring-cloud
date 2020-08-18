/**
  * @filename DiscoveryManagerController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

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
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping("/offline")
	public String offline() {
		return "";
	}
	
}
