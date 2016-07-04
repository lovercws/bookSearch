package com.kingbase.bookSearch.core.rmi.zookeeper;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.kingbase.bookSearch.core.rmi.service.RmiService;

/**
 * zookeeper服务消费者
 * @author ganliang
 */
public class ServiceConsumer {

	private static final Logger LOGGER = Logger.getLogger(ServiceConsumer.class);

	// 定义一个 volatile 成员变量，用于保存最新的 RMI 地址（考虑到该变量或许会被其它线程所修改，一旦修改后，该变量的值会影响到所有线程）
	private volatile List<String> urlList = new ArrayList<>();

	// 构造器
	public ServiceConsumer(RmiService service) {
		// 连接 ZooKeeper 服务器并获取 ZooKeeper 对象
		ZookeeperUtil zookeeperUtil = new ZookeeperUtil();
		ZooKeeper zk = zookeeperUtil.connectServer(service.getZookeeperURL(), service.getZookeeperTimeout());
		if (zk != null) {
			watchNode(zk,service.getZookeeperPath()); // 观察 服务 节点的所有子节点并更新 urlList 成员变量
		}
	}

	// 查找 RMI 服务
	public <T extends Remote> T lookup() {
		T server = null;
		int size = urlList.size();
		if (size > 0) {
			String url;
			if (size == 1) {
				url = urlList.get(0); // 若 urlList 中只有一个元素，则直接获取该元素
				LOGGER.info("using only url: {}" + url);
			} else {
				url = urlList.get(ThreadLocalRandom.current().nextInt(size)); // 若
				LOGGER.info("using random url: {}" + url);
			}
			server = lookupService(url); // 从 JNDI 中查找 RMI 服务
		}
		return server;
	}

	// 观察服务 节点下所有子节点是否有变化
	private void watchNode(final ZooKeeper zk, final String zookeeperPath) {
		try {
			List<String> nodeList = zk.getChildren(zookeeperPath, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if (event.getType() == Event.EventType.NodeChildrenChanged) {
						watchNode(zk,zookeeperPath); // 若子节点有变化，则重新调用该方法（为了获取最新子节点中的数据）
					}
				}
			});
			List<String> dataList = new ArrayList<>(); // 用于存放 所有子节点中的数据
			for (String node : nodeList) {
				byte[] data = zk.getData(zookeeperPath + "/" + node, false, null); // 获取子节点中的数据
				dataList.add(new String(data));
			}
			LOGGER.info("node data: {}" + dataList);
			urlList = dataList; // 更新最新的 RMI 地址
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
			LOGGER.info(e.getLocalizedMessage());
		}
	}

	// 在 JNDI 中查找 RMI 远程服务对象
	@SuppressWarnings("unchecked")
	private <T> T lookupService(String url) {
		T remote = null;
		try {
			remote = (T) Naming.lookup(url);
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			if (e instanceof ConnectException) {
				// 若连接中断，则使用 urlList 中第一个 RMI 地址来查找（这是一种简单的重试方式，确保不会抛出异常）
				LOGGER.info("ConnectException -> url: {}" + url);
				if (urlList.size() != 0) {
					url = urlList.get(0);
					return lookupService(url);
				}
			}
			LOGGER.info(e.getLocalizedMessage());
		}
		return remote;
	}
}