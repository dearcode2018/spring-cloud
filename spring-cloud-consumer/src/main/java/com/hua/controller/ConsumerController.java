/**
  * @filename ConsumerController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hua.bean.ResultBean;

/**
 * @type ConsumerController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/consumer")
//@EnableDiscoveryClient
public class ConsumerController extends BaseController
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
	//@GetMapping(path = "/say", produces = {})
	@RequestMapping(method = RequestMethod.GET, value = "/get")
	public ResultBean get()
	{
		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("content", "hi,i am a consumer");
		ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/speak/say", ResultBean.class, param);
		
		return resultBean;
	}
	
}
