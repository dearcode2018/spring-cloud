/**
 * 描述: 
 * PersonSearchBean.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.bean;


/**
 * 描述: 
 * @author qye.zheng
 * 
 * PersonSearchBean
 */
@SuppressWarnings({"serial"})
public final class PersonSearchBean extends BaseBean
{
	private String name;
	
	private String password;

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
