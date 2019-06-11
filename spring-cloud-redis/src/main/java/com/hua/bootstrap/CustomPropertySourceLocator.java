/**
  * @filename CustomPropertySourceLocator.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.bootstrap;

import java.util.Collections;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * @type CustomPropertySourceLocator
 * @description 自定义属性源 加载器
 * @author qianye.zheng
 */
public class CustomPropertySourceLocator implements PropertySourceLocator
{

	/**
	 * @description 
	 * @param environment
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public PropertySource<?> locate(Environment environment)
	{
		//System.out.println(environment.getProperty("spring.profiles.active", List.class));
		//System.out.println("CustomPropertySourceLocator.locate(): " + environment.getProperty("spring.application.name"));
		System.out.println("CustomPropertySourceLocator.locate(): " + environment.getProperty("spring.profiles.active"));
		PropertySource<?> source = new MapPropertySource("customProperty", 
				Collections.<String, Object>singletonMap("property.from.sample.custom.source", "hi, custom property source"));
		
		return source;
	}

}
