/**
 * 描述: 
 * ProjectBackupStarter.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.backup.starter;

import org.junit.Test;

import com.hua.backup.driver.BackupDriver;

/**
 * 描述: 当前项目备份 - 启动器
 * @author  qye.zheng
 * ProjectBackupStarter
 */
public final class ProjectBackupStarter
{


	

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
		BackupDriver.backupProject();
		
	}

}
