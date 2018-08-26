/**
 * 描述: 
 * PersonController.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.controller.spring;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hua.bean.PersonSearchBean;
import com.hua.bean.ResultBean;
import com.hua.controller.BaseController;
import com.hua.properties.ConnectionProperties;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * PersonController
 */
// 控制器
// 使用@RestController不能正常启动
//@RestController
//@Controller
//@RequestMapping(value={"/PersonController"}, method = RequestMethod.GET)
//@RequestMapping(value={"/"})
//@EnableAutoConfiguration
//@SpringBootApplication
//@ComponentScan
/*
 * @PropertySource 引入指定的配置文件，然后通过 @Value注入值
 */
//@PropertySource("classpath:/conf/properties/spring-boot.properties")
//@PropertySource("classpath:/conf/properties/spring-boot.properties")
//@Configuration
//@EnableConfigurationProperties(ConnectionProperties.class)
//@Profile("dev")
public class PersonController extends BaseController
{
	
	/**
	 * 可以通过注入该对象来获取SpringApplication.run 启动时传入的参数
	 */
	@Resource
	private ApplicationArguments args;
	
	@Value("${system.name}")
	private String systemName;
	
	@Value("${my.secret}")
	private String secret;
	
	@Value("${my.number}")
	private Integer number;
	
	@Value("${my.bignumber}")
	private Long bignumber;
	
	@Value("${my.uuid}")
	private String uuid;
	
	@Value("${my.number.less.than.ten}")
	private Integer lessThanTen;
	
	@Value("${my.number.int.range}")
	private Integer range10To25;
	
	//@Value("${jdbc.username}")
	//private String jdbcUsername;
	
	@Resource
	private ConnectionProperties connectionProperties;

	
	
	/**
	 * @RestController (二选一)
	 * @Controller (二选一)
	 * @EnableAutoConfiguration
	 * 
	 * 以下选择其一即可
	 * @EnableAutoConfiguration
	 * @SpringBootApplication
	 * @Configuration
	 * @ComponentScan
	 * 
	 */
	
	/*
	 *  禁用指定的自动配置
	 *  @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
	 * 若类路径不在class下，则使用ecludeName指定全路径即可.
	 * @EnableAutoConfiguration(excludeName={xx.xx.DataSourceAutoConfiguration})
	 */
	
	/**
	 * 
	 * @description 启动单个控制器
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args)
	{

		// 启动 spring web
		//SpringApplication.run(PersonController.class, "xxxxa");
		/*
		 * non option 参数: 字符串或字符串数组
		 * option 参数: 每个参数名称以--开头，命令行方式一样
		 */
		// 非选项参数
		//SpringApplication.run(PersonController.class, "haha");
		// 选项参数: 一个参数
		//SpringApplication.run(PersonController.class, "--name=zhangsan");
		// 选项参数: 多个参数
		SpringApplication.run(PersonController.class, "--name=zhangsan", "--age=12");
		// SpringApplication.run(PersonController.class, args);
		// 禁用命令行参数
		
		//SpringApplication.BANNER_LOCATION_PROPERTY_VALUE
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param request
	 * @param response
	 * @param searchBean
	 * @return
	 */
	@RequestMapping(value={"/get"}, method = {RequestMethod.GET})
	@ResponseBody
	public ResultBean get(final HttpServletRequest request, 
			final HttpServletResponse response, final PersonSearchBean searchBean) {
		
		/*
		 * 		SpringApplication.run(PersonController.class, "xxxxa");
		 */
		log.info("get =====> systemName = " + systemName);
		//log.info("get =====> " + args.getNonOptionArgs().get(0));
		log.info("get =====> " + args.getOptionNames().size());
		log.info("get =====> " + args.getOptionValues("name").get(0));
		log.info("get =====> " + args.getOptionValues("age").get(0));
		
		log.info("get =====>secret = " + secret);
		log.info("get =====>number = " + number);
		log.info("get =====>bignumber = " + bignumber);
		log.info("get =====>uuid = " + uuid);
		log.info("get =====>lessThanTen = " + lessThanTen);
		log.info("get =====>range10To25 = " + range10To25);
		//log.info("get =====> jdbcUsername = " + jdbcUsername);
		log.info("get =====> " + connectionProperties.getUsername());
		//log.info("get =====> name = " + searchBean.getName());
		//log.info("get =====> password = " + searchBean.getPassword());
		ResultBean result = new ResultBean();
		result.setMessage("收到[" + searchBean.getName() + "]的请求");
		result.setMessageCode("205");
		result.setSuccess(true);
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param request
	 * @param response
	 * @param searchBean
	 * @return
	 */
	@RequestMapping(value={"/postNotInBody"}, method = {RequestMethod.POST})
	@ResponseBody
	public ResultBean postNotInBody(final HttpServletRequest request, 
			final HttpServletResponse response, final PersonSearchBean searchBean) {
		/*
		 * @RequestBody 注解: 处理放在请求消息体中的报文，格式由客户端的Content-Type参数决定
		 */
		log.info("postNotInBody =====> name = " + searchBean.getName());
		log.info("postNotInBody =====> password = " + searchBean.getPassword());
		ResultBean result = new ResultBean();
		result.setMessage("收到[" + searchBean.getName() + "]的请求");
		result.setMessageCode("205");
		result.setSuccess(true);
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param request
	 * @param response
	 * @param searchBean
	 * @return
	 */
	@RequestMapping(value={"/postInBody"}, method = {RequestMethod.POST})
	@ResponseBody
	public ResultBean postInBody(final HttpServletRequest request, 
			final HttpServletResponse response, @RequestBody(required = true) final PersonSearchBean searchBean) {
		/*
		 * @RequestBody 注解: 处理放在请求消息体中的报文，格式由客户端的Content-Type参数决定
		 */
		log.info("postInBody =====> name = " + searchBean.getName());
		log.info("postInBody =====> password = " + searchBean.getPassword());
		ResultBean result = new ResultBean();
		result.setMessage("收到[" + searchBean.getName() + "]的请求");
		result.setMessageCode("205");
		result.setSuccess(true);
		
		return result;
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param request
	 * @param response
	 * @param searchBean
	 * @return
	 */
	@RequestMapping(value={"/getAndPost"}, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBean getAndPost(final HttpServletRequest request, 
			final HttpServletResponse response, final PersonSearchBean searchBean) {
		log.info("getAndPost =====> name = " + searchBean.getName());
		log.info("getAndPost =====> password = " + searchBean.getPassword());
		ResultBean result = new ResultBean();
		result.setMessage("收到[" + searchBean.getName() + "]的请求");
		result.setMessageCode("205");
		result.setSuccess(true);
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param request
	 * @param response
	 * @param searchBean
	 * @return
	 */
	@RequestMapping(value={"/search"}, method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResultBean search(final HttpServletRequest request, 
			final HttpServletResponse response, final PersonSearchBean searchBean) {
		ResultBean result = new ResultBean();
		result.setMessage("收到[" + searchBean.getName() + "]的请求");
		result.setMessageCode("205");
		result.setSuccess(true);
		
		return result;
	}
	
}
