/**
  * @filename UserClient.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hua.entity.User;

/**
 * @type UserClient
 * @description 
 * @author qianye.zheng
 */
// value 指定客户端名称, url指定服务地址/主机名
@FeignClient(value = "users", fallback = UserClientFallback.class, 
// 通过回退工厂了解回退的原因
fallbackFactory = UserClientFallbackFactory.class, 
// 关闭primary
primary =false)
public interface UserClient
{
	

	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(method = {RequestMethod.GET}, value = {"/store/get"})
	List<User> get();
	
	/**
	 * 
	 * @description 
	 * @param id
	 * @param bean
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(method = {RequestMethod.POST}, value = {"/store/update/{id}"}, 
			consumes = {"application/json;charset=utf-8"})
	User update(@PathVariable("id") final String id, final User bean);
	
	
	
	
	
}
