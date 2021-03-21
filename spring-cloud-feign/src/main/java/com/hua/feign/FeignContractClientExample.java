/**
  * @filename FeignContractClientExample.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import com.hua.entity.User;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

/**
 * @type FeignContractClientExample
 * @description Feign自身的协议，示例，接口不可调用
 * @author qianye.zheng
 */
// value 指定客户端名称, url指定服务地址/主机名
@FeignClient(name = "user-service")
public interface FeignContractClientExample
{
	

	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@RequestLine("GET /store/fetch/{param1}/{param2}")
	List<User> fetch(@Param("param1") final String param1, @Param("param2") final int param2);
	
	/**
	 * 
	 * @description 
	 * @param id
	 * @param bean
	 * @return
	 * @author qianye.zheng
	 */
	@RequestLine("POST /store/update/{id}/")
	User update(@Param("id") final String id, final User bean);
	
	/**
	 * 
	 * @description 
	 * @param param
	 * get请求方式，封装为POJO的时候，须标注@QueryMap
	 * @return
	 * @author qianye.zheng
	 */
	@RequestLine("GET /store/get")
	User get(@QueryMap final User param);
	
	
	
}
