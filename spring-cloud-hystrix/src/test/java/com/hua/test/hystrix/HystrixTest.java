/**
 * 描述: 
 * HystrixTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.hystrix;

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

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hua.ApplicationStarter;
import com.hua.bean.ResultBean;
import com.hua.hystrix.LocalService;
import com.hua.hystrix.ProviderFeignClient;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * HystrixTest
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
public final class HystrixTest extends BaseTest {

	
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
	@Resource
	private ProviderFeignClient providerFeignClient;
	
	@Resource
	private LocalService localService;
	
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
	//@DisplayName("test")
	@Test
	public void testHystrix() {
		try {
			System.out.println("begin");
			int total = 200;
			for (int i = 0; i < total; i++) {
				ResultBean result = providerFeignClient.speakSay1("你好, abc");
				assertTrue(null != result);
				System.out.println(JacksonUtil.writeAsString(result));
			}
			
		} catch (Exception e) {
			log.error("testHystrix =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: Feign客户端执行 熔断机制
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testFeignHystrix() {
		try {
			System.out.println("begin");
			int total = 200;
			for (int i = 0; i < total; i++) {
				ResultBean result = providerFeignClient.speakSay1("你好, abc");
				assertTrue(null != result);
				System.out.println(JacksonUtil.writeAsString(result));
			}
			
		} catch (Exception e) {
			log.error("testFeignHystrix =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: Feign客户端执行 熔断机制
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testFeignHystrix2() {
		try {
			System.out.println("begin");
			int total = 200;
			for (int i = 0; i < total; i++) {
				ResultBean result = providerFeignClient.speakSay2("你好, abc");
				assertTrue(null != result);
				System.out.println(JacksonUtil.writeAsString(result));
			}
			
		} catch (Exception e) {
			log.error("testFeignHystrix2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 本地服务执行 熔断机制
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testLocalServiceHystrix() {
		try {
			System.out.println("begin");
			ResultBean result = localService.localOnly(true);
			assertTrue(null != result);
			System.out.println(JacksonUtil.writeAsString(result));
			
		} catch (Exception e) {
			log.error("testLocalServiceHystrix =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 本地服务执行 熔断机制
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testLocalServiceHystrix2() {
		try {
			System.out.println("begin");
			ResultBean result = localService.localOnly2(10);
			assertTrue(null != result);
			System.out.println(JacksonUtil.writeAsString(result));
			
		} catch (Exception e) {
			log.error("testLocalServiceHystrix2 =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 本地服务执行 熔断机制
	 * 循环调用，出现失败，触发 熔断器打开
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testLocalServiceHystrix3() {
		try {
			System.out.println("begin");
			int total = 99999999;
			for (int i = 0; i < total; i++) {
				TimeUnit.MILLISECONDS.sleep(500);
				ResultBean result = localService.localOnly2(10);
				assertTrue(null != result);
				System.out.println(JacksonUtil.writeAsString(result));
			}
			
		} catch (Exception e) {
			log.error("testLocalServiceHystrix2 =====> ", e);
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
