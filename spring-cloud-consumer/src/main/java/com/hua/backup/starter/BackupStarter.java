/**
 * 描述: 
 * BackupStarter.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.backup.starter;

import org.junit.Test;

import com.hua.backup.driver.BackupDriver;

/**
 * 描述: 备份(综合多种用途) - 启动器
 * @author  qye.zheng
 * BackupStarter
 */
public final class BackupStarter
{

	private String projectName;
	
	private String wsName;

	/**
	 * 
	 * 描述: 备份指定的项目
	 * @author qye.zheng
	 */
	@Test
	public void startProject()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		//projectName = "spring-ioc-xml";
		//projectName = "mzts";
		
		projectName = "copy";
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		BackupDriver.backupProject(projectName);
		
	}
	
	/**
	 * 
	 * 描述: 备份指定的工作集
	 * @author qye.zheng
	 */
	@Test
	public void startWorkingsets()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		// wsName = "orm";
		wsName = "orm";
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		BackupDriver.backupWorkingsets(wsName);
		
	}
	
	/**
	 * 
	 * 描述: 备份所有的项目(包括系统默认工作集)
	 * @author qye.zheng
	 */
	@Test
	public void startAllProject()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		BackupDriver.backupAll();
		
	}

	// 启动器模板
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
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
