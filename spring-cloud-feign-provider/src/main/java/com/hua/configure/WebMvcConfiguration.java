package com.hua.configure;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.unit.DataSize;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 
 * @type WebMvcConfiguration
 * @description MVC配置
 * @author qianye.zheng
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Value("${web.resource.static:0}")
	private String[] staticResource;
	
	/* 请求体最大值，单位: K */
	@Value("${data.request.maxSizeK:5000}")
	private Integer maxRequestSizeK;
	
	/* 上传文件最大值，单位: K */
	@Value("${data.fileUpload.maxSizeK:5000}")
	private Integer maxUploadFileSizeK;
	
	/**
	 * @description 
	 * @param converters
	 * @author qianye.zheng
	 */
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Iterator<HttpMessageConverter<?>> it = converters.iterator();
		while (it.hasNext()) {
			HttpMessageConverter<?> e = it.next();
			/*
			 * // 顺带设置了编码 if (e instanceof StringHttpMessageConverter) {
			 * ((StringHttpMessageConverter)
			 * e).setDefaultCharset(StandardCharsets.UTF_8); } // 在客户端没有设置
			 * "Accept", "application/json"，服务端设置UTF-8编码解决中文乱码问题 if (e
			 * instanceof MappingJackson2HttpMessageConverter) {
			 * ((MappingJackson2HttpMessageConverter)
			 * e).setDefaultCharset(StandardCharsets.UTF_8); }
			 */
			// 将所有转换器的默认编码设置为 UTF-8
			if (e instanceof AbstractHttpMessageConverter) {
				((AbstractHttpMessageConverter<?>) e).setDefaultCharset(StandardCharsets.UTF_8); 
			}
			if (e instanceof FormHttpMessageConverter) {
				((FormHttpMessageConverter) e).setCharset(StandardCharsets.UTF_8); 
			}
		}
	}

	/**
	 * 
	 * spring.servlet.multipart.max-file-size = 100MB
	 * spring.servlet.multipart.max-request-size = 150MB
	 */
	
	/**
	 * 
	 * @description 文件上传配置
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		final MultipartConfigFactory factory = new MultipartConfigFactory();
		// 请求体最大值
		factory.setMaxRequestSize(DataSize.ofKilobytes(maxRequestSizeK));
		// 上传文件最大值
		factory.setMaxFileSize(DataSize.ofKilobytes(maxUploadFileSizeK));
		
		return factory.createMultipartConfig();
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
/*	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		final CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		// 5M
		resolver.setMaxUploadSize(5000000);
		
		return resolver;
	}*/
	
	
	/**
	 * 
	 * @description 
	 * @param registry
	 * @author qianye.zheng
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	/* swagger */
        registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
	@Bean
	public Converter<String, LocalDate> localDateConverter() {
		return new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(String s) {
				return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
		};
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public RestTemplate restTemplate() {
	    final RestTemplate template = new RestTemplate();
	    final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	    // 超时时间
	    factory.setConnectTimeout(60 * 1000);
	    factory.setReadTimeout(60 * 1000);
	    template.setRequestFactory(factory);
	    
	    return template;
	}
	
}
