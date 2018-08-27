/**
  * @filename CallProviderService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hua.bean.ResultBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

 /**
 * @type CallProviderService
 * @description 
 * @author qianye.zheng
 */
@Service
public class CallProviderService
{

	@Resource
	private RestTemplate restTemplate;
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	/* restTemplate.负载均衡调用失败后，则调用fallbackMetho指定的方法，容错 */
	@HystrixCommand(fallbackMethod = "defaultCall")
	public ResultBean call()
	{
		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("content", "hi,i am a consumer");
		ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/speak/say", ResultBean.class, param);
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	public ResultBean defaultCall()
	{
		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("content", "hi,i am a consumer");
		ResultBean resultBean = new ResultBean();
		resultBean.setMessage("出错了，来自histrix的容错提醒");
		
		return resultBean;
	}
	
}
