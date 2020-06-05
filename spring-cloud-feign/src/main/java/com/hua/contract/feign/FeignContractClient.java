/**
  * @filename FeignContractClient.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.contract.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.hua.bean.ResultBean;
import com.hua.configuration.FeignConfig4Provider;
import com.hua.entity.User;

import feign.QueryMap;
import feign.RequestLine;

/**
 * @type FeignContractClient
 * @description 
 * @author qianye.zheng
 */
/* name设置服务ID，通过eureka调用时的服务名 */
/*
 * FeignContract 不能使用Spring的@FeignClient
 */
//@FeignClient(name = "xx", url = "http://127.0.0.1:7070", path = "/spring-cloud-provider", configuration = FeignConfig4Provider.class)
public interface FeignContractClient
{
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	@RequestLine("GET /speak/get")
	//ResultBean get(@Param("content") final String content);
	ResultBean get(final String content);
	
	/**
	 * 
	 * @description 
	 * @param param
	 * @return
	 * @author qianye.zheng
	 */
	@RequestLine("GET /speak/getMany")
	ResultBean getMany(@QueryMap final User param);
	
	/**
	 * 
	 * @description 
	 * @param param
	 * @return
	 * @author qianye.zheng
	 */
	@RequestLine("POST /speak/post")
	ResultBean post(final User param);
	
	
}
