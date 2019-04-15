/**
  * @filename RibbonClientDefaultConfigurationConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClients;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ConfigurationBasedServerList;

/**
 * @type RibbonClientDefaultConfigurationConfig
 * @description 
 * @author qianye.zheng
 */
@RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
public class RibbonClientDefaultConfigurationConfig
{
	
	public static class BazServiceList extends ConfigurationBasedServerList
	{
		public BazServiceList(final IClientConfig config)
		{
			super.initWithNiwsConfig(config);
		}
	}
	
}
