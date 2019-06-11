/**
  * @filename FooConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @type FooConfig
 * @description 
 * @author qianye.zheng
 */
@RefreshScope
@Configuration
@Data
public class FooConfig
{
	
	@Value("${custome.foo.name}")
	private String value;
	
}
