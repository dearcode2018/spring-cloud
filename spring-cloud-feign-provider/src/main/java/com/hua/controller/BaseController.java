/**
 * 描述: 
 * BaseController.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * BaseController
 */
//@Controller
public abstract class BaseController {
	
	/* apache commons log */
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	/* 临时文件目录 */
	@Value("${file.temporary.path:/tmp/}")
	protected String temporaryPath;
	
}
