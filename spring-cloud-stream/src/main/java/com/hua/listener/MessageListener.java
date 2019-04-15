/**
  * @filename MessageListener.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.listener;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import com.hua.entity.User;
import com.hua.util.JacksonUtil;

/**
 * @type MessageListener
 * @description 
 * @author qianye.zheng
 */
@Component
public class MessageListener
{

	/**
	 * 
	 * @description 
	 * @param bean
	 * @author qianye.zheng
	 */
	@StreamListener(Sink.INPUT)
	public void process(final User bean)
	{
		System.out.println(JacksonUtil.writeAsString(bean));
	}
	
}
