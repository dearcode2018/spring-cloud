/**
 * 描述: 
 * ConsumerTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.boot;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.hua.bean.ResultBean;
import com.hua.service.CallProviderService;
import com.hua.start.ConsumerStarter;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ConsumerTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConsumerStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@MapperScan(basePackages = {"com.hua.mapper"})
public class ConsumerTest extends BaseTest {

	//@Resource
	//private PersonDao personDao;
	
	@Resource
	private RestTemplate restTemplate;
	
	//@Resource
	//private SpeakClient speakClient;

	@Resource
	private CallProviderService callProviderService;
	
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
			ResultBean resultBean = restTemplate.getForObject("http://spring-cloud-provider/speak/say", ResultBean.class, param);
			
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
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
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
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
