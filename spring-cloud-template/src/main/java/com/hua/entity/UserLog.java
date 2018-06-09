/**
 * 描述: 
 * Login.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * 描述: 用户日志
 * 
 * @author qye.zheng
 * Login
 */
public final class UserLog extends BaseEntity {

	 /* long */
	private static final long serialVersionUID = 1L;
	
	/* 登录-用户名 */
	private String username;
	
	/* 用户类型 */
	private UserType type;
	
	/* 本次登录-时间 */
	private Timestamp loginTime;
	
	/* 本次登录-IP地址 */
	private String loginIp;
	
	/** 无参构造方法 */
	public UserLog() {}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the loginTime
	 */
	public Timestamp getLoginTime()
	{
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Timestamp loginTime)
	{
		this.loginTime = loginTime;
	}

	/**
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * @return the type
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(UserType type) {
		this.type = type;
	}
	
}
