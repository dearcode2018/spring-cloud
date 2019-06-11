/**
 * 描述: 
 * LoadProperty.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean;


/**
 * 描述: 公共 - 拷贝参数
 * @author  qye.zheng
 * LoadProperty
 */
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

	/**
	 * @return the name
	 */
	public final String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public final String getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public final void setValue(String value)
	{
		this.value = value;
	}
	
}
