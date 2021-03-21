/**
  * @filename UserClient.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.mvc;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	@GetMapping("/store/get")
	List<User> get();
	
	/**
	 * 
	 * @description 
	 * @param id
	 * @param bean
	 * @return
	 * @author qianye.zheng
	 */
	@PostMapping(value = "/store/update/{id}", consumes = {"application/json;charset=utf-8"})
	User update(@PathVariable("id") final String id, final User bean);
	
	/**
	 * 
	 * @description 
	 * @param param
	 * get请求方式，封装为POJO的时候，须标注@SpringQueryMap
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping(value = "/get", consumes = {"application/json;charset=utf-8"})
	User get(@SpringQueryMap final User param);
	
	
	
}
