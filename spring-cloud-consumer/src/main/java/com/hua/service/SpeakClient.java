/**
  * @filename SpeakClient.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hua.bean.ResultBean;

/**
 * @type SpeakClient
 * @description 
 * @author qianye.zheng
 */
@FeignClient(name = "spring-cloud-provider", url = "/sepak")
public interface SpeakClient
{
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/speak/say")
	public ResultBean say(final String content);
	
}
