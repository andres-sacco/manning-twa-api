package com.twa.flights.api.clusters.service;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.springframework.stereotype.Service;

import com.twa.flights.api.clusters.configuration.zookeeper.ZooKeeperCuratorConfiguration;

@Service
public class ZooKeeperService {
	private final CuratorFramework zkClient;
	
	public ZooKeeperService(ZooKeeperCuratorConfiguration configuration) {
		this.zkClient = configuration.getClient();
	}
	
	public boolean checkIfBarrierExists(String barrierName) {
		try {
			return zkClient.checkExists().forPath(barrierName) != null;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean createBarrier(String barrierName) {
		try {
			getBarrier(barrierName).setBarrier();
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	
	public void deleteBarrier(String barrierName) {
		if(checkIfBarrierExists(barrierName)) {
			try {
				zkClient.delete().quietly().forPath(barrierName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void waitOnBarrier(String barrierName) {
		try {
			getBarrier(barrierName).waitOnBarrier(5000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DistributedBarrier getBarrier(String barrierName) {
		DistributedBarrier barrier = new DistributedBarrier(zkClient, barrierName) {
			@Override
            public synchronized void setBarrier() throws Exception {
                try {
                    zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(barrierName);
                } catch (KeeperException.NodeExistsException nodeExistsException) {
                    throw nodeExistsException;
                }
            }
		};
		
		return barrier;
	}

}
