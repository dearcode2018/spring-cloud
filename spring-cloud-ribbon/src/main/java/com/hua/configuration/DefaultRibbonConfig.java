/**
  * @filename DefaultRibbonConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ServerListSubsetFilter;

/**
 * @type DefaultRibbonConfig
 * @description 
 * @author qianye.zheng
 */
public class DefaultRibbonConfig
{
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public IRule ribbonRule()
	{
		
		
		return new BestAvailableRule();
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public IPing ribbonPing()
	{
		return new PingUrl();
	}
	
	/**
	 * 
	 * @description 
	 * @param config
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public ServerList<Server> ribbonServerList(final IClientConfig config)
	{
		return new RibbonClientDefaultConfigurationConfig.BazServiceList(config);
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public ServerListSubsetFilter serverListFilter()
	{
		return new ServerListSubsetFilter();
	}
}
