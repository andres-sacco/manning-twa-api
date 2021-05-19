package com.twa.flights.api.provider.alpha.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.twa.flights.api.provider.alpha.configuration.settings.RedisSettings;
import com.twa.flights.api.provider.alpha.dto.CityDTO;

@Configuration
@ConfigurationProperties("redis")
public class RedisConfiguration {
	private RedisSettings settings;
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(settings.getHost());
		redisStandaloneConfiguration.setPort(settings.getPort());
		
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
	@Bean
	public RedisTemplate<String, CityDTO> redisTemplate(){
		RedisTemplate<String, CityDTO> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		
		return template;
	}

}
