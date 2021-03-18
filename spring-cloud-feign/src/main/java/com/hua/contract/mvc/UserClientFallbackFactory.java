/**
  * @filename UserClientFallbackFactory.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.contract.mvc;

import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;

import com.hua.entity.User;

/**
 * @type UserClientFallbackFactory
 * @description 
 * @author qianye.zheng
 */
public class UserClientFallbackFactory implements FallbackFactory<UserClient>
{

	
	
	/**
	 * @description 
	 * @param cause
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public UserClient create(Throwable cause)
	{
		return new UserClient() {
			
			/**
			 * @description 
			 * @return
			 * @author qianye.zheng
			 */
			@Override
			public List<User> get()
			{
				System.out.println("get 异常: " + cause.getMessage());
				
				return null;
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
				System.out.println("get 异常: " + cause.getMessage());
				
				return null;
			}

			@Override
			public User get(User param) {
				return null;
			}
		};
	}

}
