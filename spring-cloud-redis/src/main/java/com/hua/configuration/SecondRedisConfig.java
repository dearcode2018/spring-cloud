/**
  * @filename SecondRedisConfig.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @type SecondRedisConfig
 * @description 
 * @author qianye.zheng
 */
@Configuration
public class SecondRedisConfig
{

	@Value("${spring.redis2.host}")
	private String host;
	
	@Value("${spring.redis2.password}")
	private String password;
	
	@Value("${spring.redis2.database}")
	private Integer database;
	
	/**
	 * 
	 * @description 
	 * @param redisConnectionFactory
	 * @return
	 * @throws UnknownHostException
	 * @author qianye.zheng
	 */
    @Bean("secondRedisTemplate")
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) 
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
    public RedisConnectionFactory connectionFactory() {
    	RedisStandaloneConfiguration re = new RedisStandaloneConfiguration();
    	re.setDatabase(database);
    	re.setPassword(password);
    	re.setHostName(host);
        JedisConnectionFactory jedis = new JedisConnectionFactory(re);

        return jedis;
    }   
    
}
