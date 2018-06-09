/**
  * @filename ConnectionProperties.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.properties;

import java.net.InetAddress;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @type ConnectionProperties
 * @description 
 * @author qianye.zheng
 */
//@ConfigurationProperties(prefix = "connnection")
public class ConnectionProperties
{
	
	private String username;
	
	private InetAddress remoteAddress;

	/**
	* @return the username
	*/
	public final String getUsername()
	{
		return username;
	}

	/**
	* @param username the username to set
	*/
	public final void setUsername(String username)
	{
		this.username = username;
	}

	/**
	* @return the remoteAddress
	*/
	public final InetAddress getRemoteAddress()
	{
		return remoteAddress;
	}

	/**
	* @param remoteAddress the remoteAddress to set
	*/
	public final void setRemoteAddress(InetAddress remoteAddress)
	{
		this.remoteAddress = remoteAddress;
	}
	
	
}
