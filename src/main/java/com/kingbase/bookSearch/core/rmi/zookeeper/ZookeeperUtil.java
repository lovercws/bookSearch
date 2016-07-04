package com.kingbase.bookSearch.core.rmi.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperUtil {
	private static final Logger LOGGER = Logger.getLogger(ZookeeperUtil.class);

	// 用于等待 SyncConnected 事件触发后继续执行当前线程
	private CountDownLatch latch = new CountDownLatch(1);

	// 连接 ZooKeeper 服务器
	public ZooKeeper connectServer(String url, int timeout) {
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(url, timeout, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if (event.getState() == Event.KeeperState.SyncConnected) {
						latch.countDown(); // 唤醒当前正在执行的线程
					}
				}
			});
			latch.await(); // 使当前线程处于等待状态
		} catch (IOException | InterruptedException e) {
			LOGGER.info(e.getLocalizedMessage());
		}
		return zk;
	}
}
