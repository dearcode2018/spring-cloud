/**
  * @filename CustomConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configure;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @type CustomConfiguration
 * @description 自定义配置
 * @author qianye.zheng
 */
//@Configuration
public class CustomConfiguration
{

	/**
	 * 配置拦截器、消息转换器、
	 */
	
	/**
	 * 
	 * @description 拦截器
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public HandlerInterceptor interceptor1()
	{
		
		return null;
	}
	
	/**
	 * 
	 * @description 消息转换器
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public HttpMessageConverters messageConverts()
	{
		HttpMessageConverter<String> convert1 = new HttpMessageConverter<String>()
		{
			/**
			 * @description 
			 * @param clazz
			 * @param mediaType
			 * @return
			 * @author qianye.zheng
			 */
			@Override
			public boolean canRead(Class<?> clazz, MediaType mediaType)
			{
				return false;
			}
			/**
			 * @description 
			 * @param clazz
			 * @param mediaType
			 * @return
			 * @author qianye.zheng
			 */
			@Override
			public boolean canWrite(Class<?> clazz, MediaType mediaType)
			{
				return false;
			}
			
			/**
			 * @description 
			 * @return
			 * @author qianye.zheng
			 */
			@Override
			public List<MediaType> getSupportedMediaTypes()
			{
				return null;
			}
			/**
			 * @description 
			 * @param clazz
			 * @param inputMessage
			 * @return
			 * @throws IOException
			 * @throws HttpMessageNotReadableException
			 * @author qianye.zheng
			 */
			@Override
			public String read(Class<? extends String> clazz,
					HttpInputMessage inputMessage)
					throws IOException, HttpMessageNotReadableException
			{
				return null;
			}
			/**
			 * @description 
			 * @param t
			 * @param contentType
			 * @param outputMessage
			 * @throws IOException
			 * @throws HttpMessageNotWritableException
			 * @author qianye.zheng
			 */
			@Override
			public void write(String t, MediaType contentType,
					HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException
			{
			}
		};
		HttpMessageConverters converts = new HttpMessageConverters(convert1);
	
		return converts;
	}
	
	/**
	 * 
	 * @description 错误页面注册
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public ErrorPageRegistrar errorPageRegistrar()
	{
		return new ErrorPageRegistrar()
				{
					/**
					 * @description 
					 * @param registry
					 * @author qianye.zheng
					 */
					@Override
					public void registerErrorPages(ErrorPageRegistry registry)
					{
						registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
					}
				};
	}
	
	/**
	 * 
	 * @description 跨域策略
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			/**
			 * @description 
			 * @param registry
			 * @author qianye.zheng
			 */
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				//WebMvcConfigurer.super.addCorsMappings(registry);
				registry.addMapping("/api/**");
			}
		};
	}
	
	
	
	
	
}
