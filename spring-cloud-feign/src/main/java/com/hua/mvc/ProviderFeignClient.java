/**
  * @filename ProviderFeignClient.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.mvc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.hua.bean.ResultBean;
import com.hua.configuration.FeignConfig4Provider;
import com.hua.entity.User;

/**
 * @type ProviderFeignClient
 * @description 
 * @author qianye.zheng
 */
/* name设置服务ID，通过eureka调用时的服务名 */
@FeignClient(name = "feign-service-name", url = "http://127.0.0.1:7070",
path = "spring-cloud-provider",
configuration = FeignConfig4Provider.class)
public interface ProviderFeignClient
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
	ResultBean speakSay(@RequestParam("content") final String content);
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	// 注意@RequestHeader 头部的值不能传中文，会存在乱码问题，通常，头部也不是用来传中文字符的
	// headers设置的是固定头部，动态头部使用@RequestHeader
	@GetMapping(value = "/speak/get", headers = {"someKey=abc2934"})
	ResultBean get(@RequestParam("content") final String content, @RequestHeader("type") final String type);
	
	/**
	 * 
	 * @description 
	 * @param param
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping("/speak/getMany")
	ResultBean getMany(@SpringQueryMap final User param);
	
	/**
	 * 
	 * @description 
	 * @param param
	 * @return
	 * @author qianye.zheng
	 */
	@PostMapping("/speak/post")
	ResultBean post(final User param);
	
}
