/**
  * @filename RefreshBean.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.bean;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hua.entity.User;

import lombok.Data;

/**
 * @type RefreshBean
 * @description 
 * @author qianye.zheng
 */
@Data
@RefreshScope
@Configuration
//@Component
public class RefreshBean
{
	
	private boolean flag;
	
	/**
	 * @description 构造方法
	 * @author qianye.zheng
	 */
	public RefreshBean()
	{
		System.out.println("RefreshBean.RefreshBean()");
	}
	
	{
		
		System.out.println("RefreshBean.enclosing_method()");
		
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	//@RefreshScope
	public User user()
	{
		User user = new User();
		System.out.println("RefreshBean.user()");
		
		return user;
	}
	
}
