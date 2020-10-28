package com.hua.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

	
	/* 文件上传最大值，单位: 千字节 */
	@Value("${file.upload.maxSizeKilloByte:5000}")
	private int maxUploadSizeKilloByte;
	
	@Value("${sys.temp.dir:/data/tmp}")
	private String tempDir;
	
	/**
	 * @description 拦截器声明
	 * @param registry
	 * @author qianye.zheng
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
	}
	
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
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		final CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		// 5M
		resolver.setMaxUploadSize(maxUploadSizeKilloByte * 1000L);
		final Resource resource = new FileSystemResource(tempDir);
		try {
			
			/*
			 * 文件上传，临时存储目录
			 * 若不设置，则在应用启动的时候，默认创建一个
			 */
			//resolver.setUploadTempDir(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resolver;
	}
	
	/**
	 * 
	 * @description 
	 * @param registry
	 * @author qianye.zheng
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	/*  */
/*    	registry.addResourceHandler("/resource/**").addResourceLocations("/resource/");
        registry.addResourceHandler("/dist/**").addResourceLocations("/dist/");       
        registry.addResourceHandler("/newStore/**").addResourceLocations("/newStore/");       
        registry.addResourceHandler("/staffModel/**").addResourceLocations("/staffModel/");      
        registry.addResourceHandler("/weChat/**").addResourceLocations("/weChat/");*/
    	//registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static1/");       
    	
    }
    
}
