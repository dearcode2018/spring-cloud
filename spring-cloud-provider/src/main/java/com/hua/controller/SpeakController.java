/**
  * @filename SpeakController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hua.bean.ResultBean;

 /**
 * @type SpeakController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/speak")
//@EnableDiscoveryClient
public class SpeakController extends BaseController
{
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	//@GetMapping(path = "/say", produces = {})
	@RequestMapping(method = RequestMethod.GET, value = "/say")
	public ResultBean say(final String content)
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setId("20180825");
		resultBean.setMessage("Say: " + content);
		resultBean.setSuccess(true);
		resultBean.setMessageCode("200");
		
		return resultBean;
	}
}
