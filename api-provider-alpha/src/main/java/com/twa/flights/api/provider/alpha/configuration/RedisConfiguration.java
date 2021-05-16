package com.twa.flights.api.provider.alpha.configuration;

import com.twa.flights.api.provider.alpha.configuration.settings.RedisSettings;
import com.twa.flights.api.provider.alpha.dto.CityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@ConfigurationProperties // (prefix = "redis")
public class RedisConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(RedisConfiguration.class);

    private RedisSettings redis;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        LOG.info("ALPHA API -> Redis Host: {}, Redis Port: {}", redis.getHost(), redis.getPort());
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redis.getHost(),
                redis.getPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, CityDTO> redisTemplate() {
        LOG.info("\n\n\n********* ALPHA API -> Creating redis Template ********* \n\n\n");
        RedisTemplate<String, CityDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        return redisTemplate;
    }

    public RedisSettings getRedis() {
        return redis;
    }

    public void setRedis(RedisSettings redis) {
        this.redis = redis;
    }
}