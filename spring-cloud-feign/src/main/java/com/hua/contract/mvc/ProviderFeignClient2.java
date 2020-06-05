/**
  * @filename ProviderFeignClient2.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.contract.mvc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hua.bean.Param1;
import com.hua.bean.ResultBean;
import com.hua.configuration.FeignConfig4Provider2;

/**
 * @type ProviderFeignClient2
 * @description 
 * @author qianye.zheng
 */
/* name设置服务ID，通过eureka调用时的服务名，qualifier 指定 FeignClient的别名
 contextId: 当有个多个Feign客户端绑定同一个服务时，每个客户端需要声明contextId
 属性
 *
 */
@FeignClient(name = "spring-cloud-provider", configuration = FeignConfig4Provider2.class, contextId = "a")
public interface ProviderFeignClient2
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
	/*  用 SpringQueryMap 代替 QueryMap，作为GET查询参数映射，查询参数可以封装成一个Bean */
	ResultBean speakSay2(final /* @RequestParam("content") */ @SpringQueryMap Param1 param);
	
	
}
