/**
  * @filename ProviderEurekaFeignClient3.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hua.bean.ResultBean;
import com.hua.feign.config.FeignConfig4Provider2;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import feign.hystrix.FallbackFactory;

/**
 * @type ProviderEurekaFeignClient3
 * @description 
 * @author qianye.zheng
 */
/* name设置服务ID，通过eureka调用时的服务名，qualifier 指定 FeignClient的别名
 contextId: 当有个多个Feign客户端绑定同一个服务时，每个客户端需要声明contextId
 属性
 *
 */
@FeignClient(name = "spring-cloud-provider", 
configuration = FeignConfig4Provider2.class, contextId = "c"
// fallback 和 fallbackFactory 使用其中一个
//, fallback = ProviderFallback.class
//, fallbackFactory = ProviderFallbackFactory.class, primary = false
 )
public interface ProviderEurekaFeignClient3
{
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
	//@HystrixCommand()
	@HystrixCommand(commandProperties = {
		     @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
		})
	@GetMapping("/speak/say")
	/* 注意单个参数需要加上@RequestParam，否则报 405异常
	 * (feign.FeignException: status 405 reading)  */
	ResultBean speakSay(final @RequestParam("content") String content);
	
	/**
	 * 
	 * @type ProviderFallback
	 * @description 
	 * @author qianye.zheng
	 */
	@Component
	static class ProviderFallback implements ProviderEurekaFeignClient3
	{
		/**
		 * @description 
		 * @param content
		 * @return
		 * @author qianye.zheng
		 */
		@Override
		public ResultBean speakSay(String content)
		{
			ResultBean resultBean = new ResultBean();
			resultBean.setSuccess(false);
			resultBean.setMessage("hystrix 回退消息");
			
			return resultBean;
		}
	}
	
	/**
	 * 
	 * @type ProviderFallbackFactory
	 * @description 回退-携带异常消息
	 * @author qianye.zheng
	 */
	@Component
	static class ProviderFallbackFactory implements FallbackFactory<ProviderEurekaFeignClient3>
	{
		/**
		 * @description 
		 * @param cause
		 * @return
		 * @author qianye.zheng
		 */
		@Override
		public ProviderEurekaFeignClient3 create(Throwable cause)
		{
			
			return new ProviderEurekaFeignClient3() {
				/**
				 * @description 
				 * @param content
				 * @return
				 * @author qianye.zheng
				 */
				@Override
				public ResultBean speakSay(String content)
				{
					ResultBean resultBean = new ResultBean();
					resultBean.setSuccess(false);
					resultBean.setMessage("hystrix 异常消息: " + cause.getMessage());
					
					return resultBean;
				}
			};
		}
	}
}
