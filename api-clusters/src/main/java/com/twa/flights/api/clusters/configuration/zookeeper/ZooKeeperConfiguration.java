package com.twa.flights.api.clusters.configuration.zookeeper;

import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZooKeeperConfiguration {

    private String host;
    private int maxRetries;
    private int timeBetweenRetries;
    private int connectionTimeout;

    @Bean
    public ZooKeeperCuratorConfiguration zookeeperConnection() {
        return new ZooKeeperCuratorConfiguration(
                CuratorFrameworkFactory.builder().connectString(host).connectionTimeoutMs(connectionTimeout)
                        .retryPolicy(new RetryNTimes(maxRetries, timeBetweenRetries)).build());
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getTimeBetweenRetries() {
        return timeBetweenRetries;
    }

    public void setTimeBetweenRetries(int timeBetweenRetries) {
        this.timeBetweenRetries = timeBetweenRetries;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

}