/**
  * @filename MyCommandLineRunner.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @type MyCommandLineRunner
 * @description 
 * @author qianye.zheng
 */
//@Component
public class MyCommandLineRunner implements CommandLineRunner
{

	/**
	 * @description 
	 * @param args
	 * @throws Exception
	 * @author qianye.zheng
	 */
	@Override
	public void run(String... args) throws Exception
	{
		System.out.println("MyCommandLineRunner: " + args[0]);
	}



}
