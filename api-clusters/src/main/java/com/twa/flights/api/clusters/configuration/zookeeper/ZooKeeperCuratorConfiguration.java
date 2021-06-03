package com.twa.flights.api.clusters.configuration.zookeeper;

import javax.annotation.PreDestroy;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class ZooKeeperCuratorConfiguration {

    private CuratorFramework client;

    @Autowired
    public ZooKeeperCuratorConfiguration(CuratorFramework client) {
        this.client = client;
        this.client.start();
    }

    public CuratorFramework getClient() {
        return client;
    }

    @PreDestroy
    public void close() {
        client.close();
    }
}
