/**
  * @filename RedisController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hua.bean.ResultBean;

/**
 * @type RedisController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/redis")
public class RedisController extends BaseController
{

	@Resource
	private StringRedisTemplate firstRedisTemplate;
	
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
		result.setMessage("值["  + firstRedisTemplate.boundValueOps("A:B").get() + "]");
		result.setMessageCode("205");
		result.setSuccess(true);
		
		return result;
	}
	
}
