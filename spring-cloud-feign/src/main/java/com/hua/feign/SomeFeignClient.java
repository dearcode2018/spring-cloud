/**
  * @filename SomeFeignClient.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hua.bean.ResultBean;

/**
 * @type SomeFeignClient
 * @description 
 * @author qianye.zheng
 */
/* name设置服务ID，通过eureka调用时的服务名 */
public interface SomeFeignClient
{
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping("/speak/say")
	/* 注意单个参数需要加上@RequestParam，否则报 405异常
	 * (feign.FeignException: status 405 reading)  */
	ResultBean speakSay(final @RequestParam("content") String content);
	
	
}
