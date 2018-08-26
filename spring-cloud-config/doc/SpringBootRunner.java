/**
 * 描述: 
 * SpringBootRunner.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 描述: Spring Boot 启动器/运行器
 * @author  qye.zheng
 * 
 * SpringBootRunner
 */
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
public class SpringBootRunner
{

	/**
	 * 注意要在根包下启动，因为Spring Boot 默认是扫描该目录以及其下的目录，而不会扫描其
	 * 上一级目录，因此必须放置好位置
	 */
	
	/**
	 * 
	 * @description 
	 * @param args
	 * @author qianye.zheng
	 */
/*	public static void main(String[] args)
	{	
		// 启动 spring web
		SpringApplication.run(SpringBootRunner.class);
	}*/
	

	// 启动器模板
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void start()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		
		
	}

}
