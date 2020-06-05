/**
  * @filename UserClientFallback.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.contract.mvc;

import java.util.ArrayList;
import java.util.List;

import com.hua.entity.User;

/**
 * @type UserClientFallback
 * @description 
 * @author qianye.zheng
 */
public class UserClientFallback implements UserClient
{

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public List<User> get()
	{
		System.out.println("UserClientFallback.get()");
		List<User> list = new ArrayList<>();
		User user = new User();
		user.setUsername("fallback method");
		list.add(user);
		
		return list;
	}

	/**
	 * @description 
	 * @param id
	 * @param bean
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public User update(String id, User bean)
	{
		System.out.println("UserClientFallback.update()");
		return null;
	}

	/**
	 * @description 
	 * @param param
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public User get(User param) {
		return null;
	}

}
