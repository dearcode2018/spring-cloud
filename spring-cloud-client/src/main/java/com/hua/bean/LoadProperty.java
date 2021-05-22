/**
 * 描述: 
 * LoadProperty.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean;

import lombok.Data;

/**
 * 描述: 公共 - 拷贝参数
 * @author  qye.zheng
 * LoadProperty
 */
@Data
public class LoadProperty
{
	
	/*
	 名称(由子类来提供值) - 正则
	 */
	private String name;
	
	/*
	 名称(由子类来提供值) - 符合正则条件，替换值
	 */
	private String value;
	
}
