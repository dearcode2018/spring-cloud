/**
 * 描述: 
 * AllWsBackupStarter.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.backup.starter;

import org.junit.Test;

import com.hua.backup.driver.BackupDriver;

/**
 * 描述: 备份所有工作集的项目 - 启动器
 * @author  qye.zheng
 * AllWsBackupStarter
 */
public final class AllWsBackupStarter
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
		BackupDriver.backupAllWs();
		
	}

}
