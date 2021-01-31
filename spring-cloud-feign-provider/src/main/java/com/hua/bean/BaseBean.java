/**
* BaseSearch.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 描述: 基本 bean
 * @author qye.zheng
 * BaseSearch
 */
public abstract class BaseBean implements Serializable {

	/* long */
	private static final long serialVersionUID = 1L;
	
	private String id;

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public String toString() {
		
		return new ReflectionToStringBuilder(this).toString();
	}
	
}
