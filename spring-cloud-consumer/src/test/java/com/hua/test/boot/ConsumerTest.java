/**
 * 描述: 
 * ConsumerTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.boot;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.hua.ApplicationStarter;
import com.hua.bean.ResultBean;
import com.hua.service.CallProviderService;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ConsumerTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
// for Junit 5.x
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration(value = "src/main/webapp")
@SpringBootTest(classes = {ApplicationStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@MapperScan(basePackages = {"com.hua.mapper"})
public final class ConsumerTest extends BaseTest {

	
	/*
	配置方式1: 
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextConfiguration(locations = {
			"classpath:conf/xml/spring-bean.xml", 
			"classpath:conf/xml/spring-config.xml", 
			"classpath:conf/xml/spring-mvc.xml", 
			"classpath:conf/xml/spring-service.xml"
		})
	@ExtendWith(SpringExtension.class)
	
	配置方式2: 	
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextHierarchy({  
		 @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),  
		 @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
		}) 
	@ExtendWith(SpringExtension.class)
	 */
	
	/**
	 * 而启动spring 及其mvc环境，然后通过注入方式，可以走完 spring mvc 完整的流程.
	 * 
	 */
	//@Resource
	//private UserController userController;
	//@Resource
	//private PersonDao personDao;
	
	@Resource
	private RestTemplate restTemplate;
	
	//@Resource
	//private SpeakClient speakClient;

	@Resource
	private CallProviderService callProviderService;
	/**
	 * 引当前项目用其他项目之后，然后可以使用
	 * SpringJunitTest模板测试的其他项目
	 * 
	 * 可以使用所引用目标项目的所有资源
	 * 若引用的项目的配置与本地的冲突或无法生效，需要
	 * 将目标项目的配置复制到当前项目同一路径下
	 * 
	 */
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCallZuul() {
		try {
			
			final Map<String, Object> param = new HashMap<String, Object>();
			param.put("content", "hi,i am a consumer");
			/*
			 * 提供者带项目名启动时，发起调用的路径必须也带上相同的路径
			 * 否则发生404错误.
			 * 第一个是服务名(注册到Eureka的 application.name)、第二个是项目名(提供者的context-path)
			 *
			 *因为有了服务名，提供者通常都不要再带上项目名来发布了.
			 */
			//ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/spring-cloud-provider/speak/say", ResultBean.class, param);
			
			/*
			 * 提供者不带项目名启动，直接拼接服务名+接口路径即可
			 */
			//ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/speak/say", ResultBean.class, param);
			/*
			 * 提供者带项目名启动，服务名+上下文路径+接口路径
			 */
			/*
			 * 调用网关，网关服务名+网关上下文路径+目标服务名+目标服务上下文路径+目标服务接口路径
			 * 
			 * 所有的向下文路径都是可以省略的
			 * 
			 * 外部(例如 postman)调用网关的方式 (网关IP+端口+网关上下文路径+目标服务名+目标服务上下文路径+目标服务接口路径)
			 * http://127.0.0.1:1010/spring-cloud-zuul/spring-cloud-provider/spring-cloud-provider/speak/say?content=CLFXX
			 * 
			 * 
			 */
			//ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-zuul/spring-cloud-zuul/spring-cloud-provider/spring-cloud-provider/speak/say?content={content}", ResultBean.class, param);
			ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-zuul/spring-cloud-zuul/spring-cloud-provider/spring-cloud-provider/speak/say?content={content}", ResultBean.class, param);
					
			System.out.println(JacksonUtil.writeAsString(resultBean));
		} catch (Exception e) {
			log.error("testCallZuul =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConsumer() {
		try {
			final Map<String, Object> param = new HashMap<String, Object>();
			/*
			 * uriVariables, 格式 url?key={keyName} keyName 则为下面的key
			 * 例如 content={content}
			 */
			param.put("content", "hi,i am a consumer");
			/*
			 * 提供者带项目名启动时，发起调用的路径必须也带上相同的路径
			 * 否则发生404错误.
			 * 第一个是服务名(注册到Eureka的 application.name)、第二个是项目名(提供者的context-path)
			 *
			 *因为有了服务名，提供者通常都不要再带上项目名来发布了.
			 */
			//ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/spring-cloud-provider/speak/say", ResultBean.class, param);
			
			/*
			 * 提供者不带项目名启动，直接拼接服务名+接口路径即可
			 */
			//ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/speak/say", ResultBean.class, param);
			/*
			 * 提供者带项目名启动，服务名+上下文路径+接口路径
			 */
			//ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/spring-cloud-provider/speak/say?content={content}", ResultBean.class, param);
			ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/speak/say?content={content}", ResultBean.class, param);
					
			System.out.println(JacksonUtil.writeAsString(resultBean));
		} catch (Exception e) {
			log.error("testConsumer =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHystrix() {
		try {
			ResultBean resultBean = callProviderService.call();

			System.out.println(JacksonUtil.writeAsString(resultBean));
		} catch (Exception e) {
			log.error("testHystrix =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFeignClient() {
		try {
			//speakClient.say("abc");
			
		} catch (Exception e) {
			log.error("testFeignClient =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testTemp")
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testCommon")
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testSimple")
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
	}

}
