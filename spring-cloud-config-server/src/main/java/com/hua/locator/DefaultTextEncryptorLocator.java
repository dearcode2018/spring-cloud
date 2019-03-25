/**
  * @filename DefaultTextEncryptorLocator.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.locator;

import java.util.Map;

import org.springframework.cloud.config.server.encryption.TextEncryptorLocator;
import org.springframework.security.crypto.encrypt.TextEncryptor;

/**
 * @type DefaultTextEncryptorLocator
 * @description 
 * @author qianye.zheng
 */
public class DefaultTextEncryptorLocator implements TextEncryptorLocator
{

	/**
	 * @description 
	 * @param keys
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public TextEncryptor locate(Map<String, String> keys)
	{
		
		
		return null;
	}

}
