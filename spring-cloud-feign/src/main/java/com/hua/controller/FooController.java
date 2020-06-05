/**
  * @filename FooController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;

import com.hua.contract.mvc.UserClient;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;

/**
 * @type FooController
 * @description 
 * @author qianye.zheng
 */
/* FeignClientsConfiguration.class是Spring Cloud Netflix提供的默认配置 */
@Import(FeignClientsConfiguration.class)
public class FooController extends BaseController
{
	
	private UserClient userClient;
	
	/**
	 * 
	 * @description 构造方法
	 * @param decoder
	 * @param encoder
	 * @param client
	 * @param contract
	 * @author qianye.zheng
	 */
	@Autowired
	public FooController(final Decoder decoder, final Encoder encoder,
			final Client client, final Contract contract)
	{
		userClient = Feign.builder().client(client).encoder(encoder)
				.decoder(decoder).contract(contract)
				// 服务的名称
				.target(UserClient.class, "http://serviceName");
	}
	
}
