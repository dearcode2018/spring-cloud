
/**
  * @filename ConfigController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hua.actuator.FooConfig;
import com.hua.bean.ResultBean;

/**
 * @type ConfigController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController
{
	
	@Resource
	private FooConfig fooConfig;
	
	/**
	 * 
	 * @description 获取值
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping({"/getValue"})
	public ResultBean getValue()
	{
		ResultBean result = new ResultBean();
		result.setMessage("值["  + fooConfig.getValue() + "]");
		result.setMessageCode("205");
		result.setSuccess(true);
		
		return result;
	}
	
}
