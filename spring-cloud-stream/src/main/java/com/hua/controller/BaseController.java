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
import org.springframework.stereotype.Controller;

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
	
	
}
