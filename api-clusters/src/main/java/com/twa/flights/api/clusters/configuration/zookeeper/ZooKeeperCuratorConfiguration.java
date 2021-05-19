package com.twa.flights.api.clusters.configuration.zookeeper;

import org.apache.curator.framework.CuratorFramework;

import javax.annotation.PreDestroy;

public class ZooKeeperCuratorConfiguration {

    private final CuratorFramework client;

    public ZooKeeperCuratorConfiguration(CuratorFramework client) {
        this.client = client;
        client.start();
    }

    @PreDestroy
    public void close() {
        client.close();
    }

    public CuratorFramework getClient() {
        return client;
    }

}