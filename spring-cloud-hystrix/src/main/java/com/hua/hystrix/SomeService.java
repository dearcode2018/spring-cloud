/**
  * @filename SomeService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.hystrix;

import org.springframework.stereotype.Service;

import com.hua.bean.ResultBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @type SomeService
 * @description 
 * @author qianye.zheng
 */
@Service
public class SomeService
{
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	// 指定失败调用的方法
	//@HystrixCommand(fallbackMethod = "fallbackMethodGet")
	@HystrixCommand(fallbackMethod = "fallbackMethodGet", commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})
	public ResultBean get()
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setMessage("成功");
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 失败默认调用此方法
	 * @return
	 * @author qianye.zheng
	 */
	public ResultBean fallbackMethodGet()
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setMessage("fallbackMethodGet");
		
		return resultBean;
	}
	
}
