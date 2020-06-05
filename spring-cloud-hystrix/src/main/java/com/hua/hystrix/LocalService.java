/**
  * @filename LocalService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.hystrix;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hua.bean.ResultBean;
import com.hua.util.JacksonUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @type LocalService
 * @description 
 * @author qianye.zheng
 */
@Service
/* @DefaultProperties 类级别的标注,标注默认的fallback方法，前提是当前类的所有方法的签名都相同
  线程池等类级别的配置，方法没有配置的则使用类级别的配置
 *  */
@DefaultProperties
public class LocalService
{
	
	@Resource
	private ProviderFeignClient providerFeignClient;
	
	@Resource
	private ProviderFeignClientWithoutFB providerFeignClientWithoutFB;
	
	/**
	 * 本地服务
	 * 
	 * 本地服务也有可能调用外部接口，或者通过FeignClient进行调用，如果FeignClient也配置了熔断，则
	 * 在调用FeignClient的过程中也会走熔断，然后返回给本地服务
	 * 
	 * fallback方法的签名需要和原先方法一样，否则会抛 FallbackDefinitionException
	 */
	
	
	/**
	 * 
	 * @description 执行失败(超时/异常，则会执行fallback方法)
	 * @return
	 * @author qianye.zheng
	 */
	// 指定失败调用的方法
	//@HystrixCommand(fallbackMethod = "fallbackMethodGet")
	@HystrixCommand(fallbackMethod = "localOnlyFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			// 自定义执行超时时间
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")}, 
			// 新建线程池，达到资源隔离目的，若不存在该属性，则使用公共的线程池.
			threadPoolKey = "newThreadPool1",
			// 线程池配置
			threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "5"),
			// maxQueueSize=-1使用同步队列，线程池忙碌时拒绝新的请求
			@HystrixProperty(name = "maxQueueSize", value = "10")})
	public ResultBean localOnly(final boolean ifThrow)
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setMessage("成功");
		if (ifThrow) {
			throw new RuntimeException("发生了异常");
		}
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 执行失败(超时/异常，则会执行fallback方法)
	 * @return
	 * @author qianye.zheng
	 */
	// 指定失败调用的方法
	//@HystrixCommand(fallbackMethod = "fallbackMethodGet")
	@HystrixCommand(fallbackMethod = "localOnlyFallback2", commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})
	public ResultBean localOnly2(final int timeoutSec)
	{
		ResultBean resultBean = new ResultBean();
		//resultBean.setMessage("成功");
		/**
		 * 不用线程休眠、打断点的方式去模拟，这样达不到效果
		 * 调用外部资源可以达到模拟效果.
		 * 线程休眠、打断点都是挂起当前线程，无法继续干活，因此无法达到效果
		 */
		ResultBean result = providerFeignClientWithoutFB.speakSay1("你好, abc");
		resultBean.setMessage(result.getMessage());
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	// 指定失败调用的方法
	//@HystrixCommand(fallbackMethod = "fallbackMethodGet")
	@HystrixCommand(fallbackMethod = "localCallRemoteFallback", commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})
	public ResultBean localCallRemote(final String param)
	{
		ResultBean resultBean = new ResultBean();
		System.out.println("begin");
		ResultBean result = providerFeignClient.speakSay1("你好, abc");
		assertTrue(null != result);
		System.out.println("远程响应(By FeignClient): " + JacksonUtil.writeAsString(result));
		resultBean.setMessage(result.getMessage());
		
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 失败默认调用此方法
	 * @return
	 * @author qianye.zheng
	 */
	public ResultBean localCallRemoteFallback(final String param)
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setMessage("fallbackMethodGet");
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 失败默认调用此方法
	 * @param ifThrow
	 * @return
	 * @author qianye.zheng
	 */
	public ResultBean localOnlyFallback(final boolean ifThrow)
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setMessage("执行异常fallback");
		
		return resultBean;
	}
	
	/**
	 * 
	 * @description 失败默认调用此方法
	 * @param timeoutSec
	 * @return
	 * @author qianye.zheng
	 */
	public ResultBean localOnlyFallback2(final int timeoutSec)
	{
		ResultBean resultBean = new ResultBean();
		resultBean.setMessage("执行失败fallback");
		
		return resultBean;
	}
	
}
