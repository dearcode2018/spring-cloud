/**
  * @filename MyApplicationRunner.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.run;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @type MyApplicationRunner
 * @description 
 * @author qianye.zheng
 */
//@Component
public class MyApplicationRunner implements ApplicationRunner
{

	/**
	 * @description 
	 * @param args
	 * @throws Exception
	 * @author qianye.zheng
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		System.out.println("MyApplicationRunner: " + args.getNonOptionArgs().get(0));
	}

}
