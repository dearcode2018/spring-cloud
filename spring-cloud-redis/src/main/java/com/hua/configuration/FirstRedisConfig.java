/**
  * @filename FirstRedisConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @type FirstRedisConfig
 * @description 
 * @author qianye.zheng
 */
/* 要刷新Bean，需要同时在类级和方法级上声明@RefreshScope才能生效，单方面声明无效 */
@RefreshScope
@Configuration
public class FirstRedisConfig
{
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.password}")
	private String password;
	
	@Value("${spring.redis.database}")
	private Integer database;
	
	/**
	 * 
	 * @description 
	 * @param redisConnectionFactory
	 * @return
	 * @throws UnknownHostException
	 * @author qianye.zheng
	 */
	@RefreshScope
    @Bean("firstRedisTemplate")
	public StringRedisTemplate stringRedisTemplate() 
	{
		 StringRedisTemplate template = new StringRedisTemplate();
		 template.setConnectionFactory(connectionFactory());

		 return template;
	}
    
    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    public RedisConnectionFactory connectionFactory() 
    {
    	RedisStandaloneConfiguration re = new RedisStandaloneConfiguration();
    	re.setDatabase(database);
    	re.setPassword(password);
    	re.setHostName(host);
        JedisConnectionFactory jedis = new JedisConnectionFactory(re);

        return jedis;
    }   
    
}
