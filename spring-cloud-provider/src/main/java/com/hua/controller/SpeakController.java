/**
  * @filename SpeakController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hua.bean.ResultBean;
import com.hua.entity.User;

 /**
 * @type SpeakController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/speak")
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
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping("/get")
	public ResultBean get(final HttpServletRequest request, final HttpServletResponse response, final String content, 
			@RequestHeader("type") final String type, @RequestHeader("someKey") final String someKey)
	{
		//System.out.println(request.getCookies()[0].getName());
		//System.out.println(request.getCookies()[0].getValue());
		try
		{
			//System.out.println("storeId = " + request.getHeader("storeId") + ", name = " + URLDecoder.decode(request.getHeader("storeName"), Constant.CHART_SET_UTF_8));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		ResultBean resultBean = new ResultBean();
		resultBean.setId("20180826");
		resultBean.setMessage("Say(说): " + content);
		resultBean.setData("headerValue = " + type + ", " + someKey);
		resultBean.setSuccess(true);
		resultBean.setMessageCode("200");
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @author qianye.zheng
	 */
	@GetMapping("/getMany")
	public ResultBean getMany(final HttpServletRequest request, final HttpServletResponse response, final User user)
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setId("20180827");
		resultBean.setMessage("用户名称: " + user.getUsername());
		resultBean.setSuccess(true);
		resultBean.setMessageCode("200");
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	@PostMapping("/post")
	public ResultBean post(final HttpServletRequest request, final HttpServletResponse response, final @RequestBody User user)
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setId("20180827");
		resultBean.setMessage("用户名称: " + user.getUsername());
		resultBean.setSuccess(true);
		resultBean.setMessageCode("200");
		
		return resultBean;
	}
	
}
