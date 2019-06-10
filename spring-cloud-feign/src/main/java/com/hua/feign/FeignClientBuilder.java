/**
  * @filename FeignClientBuilder.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hua.bean.ResultBean;
import com.hua.util.JacksonUtil;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import feign.codec.Encoder;

/**
 * @type FeignClientBuilder
 * @description 
 * @author qianye.zheng
 */
/* 使用 Spring 自带的FeignClientsConfiguration，会构建构造方法所需的Bean */
@Configuration
@Import(FeignClientsConfiguration.class)
public class FeignClientBuilder
{
	private SomeFeignClient someFeignClient;
	
	@Autowired
	public FeignClientBuilder(final Decoder decoder, final Encoder encoder, 
			final Client client, final Contract contract)
	{
		someFeignClient = Feign.builder().client(client).decoder(decoder)
				.encoder(encoder).contract(contract).requestInterceptor(new DefaultInterceptor())
				// url: http://服务名
				.target(SomeFeignClient.class, "http://spring-cloud-provider");
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void call()
	{
		ResultBean resultBean = someFeignClient.speakSay("哈哈");;
		System.out.println(JacksonUtil.writeAsString(resultBean));
	}
	
	/**
	 * 
	 * @type DefaultInterceptor
	 * @description 
	 * @author qianye.zheng
	 */
	class DefaultInterceptor implements RequestInterceptor
	{
		/**
		 * @description 
		 * @param template
		 * @author qianye.zheng
		 */
		@Override
		public void apply(RequestTemplate template)
		{
			//template.header("X-APPID", "xx");
		}
	}
}
