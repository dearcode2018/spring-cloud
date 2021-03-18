/**
  * @filename FeignClientWithFallback.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.contract.mvc;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hua.bean.ResultBean;
import com.hua.configuration.FeignConfig4Provider2;
import com.hua.contract.mvc.FeignClientWithFallback.ProviderFallbackFactory;

/**
 * @type FeignClientWithFallback
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
, fallbackFactory = ProviderFallbackFactory.class, primary = true
 )
public interface FeignClientWithFallback
{
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @return
	 * @author qianye.zheng
	 */
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
	//@Component
	static class ProviderFallback implements FeignClientWithFallback
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
	static class ProviderFallbackFactory implements FallbackFactory<FeignClientWithFallback>
	{
		/**
		 * @description 
		 * @param cause
		 * @return
		 * @author qianye.zheng
		 */
		@Override
		public FeignClientWithFallback create(Throwable cause)
		{
			
			return new FeignClientWithFallback() {
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
