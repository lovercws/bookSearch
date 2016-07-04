package com.kingbase.bookSearch.core.rmi.zookeeper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.kingbase.bookSearch.core.rmi.service.RmiService;

/**
 * zookeeper服务提供者
 * @author ganliang
 *
 */
public class ServiceProvider {

	private static final Logger LOGGER = Logger.getLogger(ServiceProvider.class);

	// 发布 RMI 服务并注册 RMI 地址到 ZooKeeper 中
	public void publish(Remote remote, RmiService service) {
		LOGGER.info("rmi发布服务.............................");
		String url = publishService(remote, service.getServiceHost(), service.getServicePort()); // 发布 RMI 服务并返回 RMI 地址
		if (url != null) {
			ZookeeperUtil zookeeperUtil = new ZookeeperUtil();
			ZooKeeper zk = zookeeperUtil.connectServer(service.getZookeeperURL(), service.getZookeeperTimeout());
			if (zk != null) {
				String zookeeperPath = service.getZookeeperPath();
				String[] paths = zookeeperPath.split("/");
				String parentPath="/";
				for (String path : paths) {
					if(!"".equals(path)){
						parentPath=parentPath+path;
						createZookeeperNode(zk,parentPath);
						parentPath=parentPath+"/";
					}
				}
				
				createNode(zk, url,service.getZookeeperPath()+service.getServicePath()); // 创建 ZNode 并将 RMI 地址放入 ZNode 上
			}
		}
	}

	// 发布 RMI 服务
	private String publishService(Remote remote, String host, int port) {
		String url = null;
		try {
			url = String.format("rmi://%s:%d/%s", host, port, remote.getClass().getName());
			LocateRegistry.createRegistry(port);
			Naming.rebind(url, remote);
			LOGGER.info("publish rmi service (url: {})"+url);
		} catch (RemoteException | MalformedURLException e) {
			LOGGER.equals(e);
		}
		return url;
	}

	// 创建 ZookeeperNode顶层节点
	private void createZookeeperNode(ZooKeeper zk, String zookeeperPath) {
		try {
			//查看服务节点是否存在 如果不存在 则创建
			Stat stat = zk.exists(zookeeperPath, false);
			if(stat==null){
				zk.create(zookeeperPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				LOGGER.info("create zookeeper node ({} => {})"+zookeeperPath);
			}
		} catch (KeeperException | InterruptedException e) {
			LOGGER.info(e.getLocalizedMessage());
		}
	}
	
	// 创建 ZNode
	private void createNode(ZooKeeper zk, String url,String servicePath) {
		try {
			byte[] data = url.getBytes();
			String path = zk.create(servicePath, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL); // 创建一个临时性且有序的 ZNode
			LOGGER.info("create zookeeper node ({} => {})"+path+"  "+url);
		} catch (KeeperException | InterruptedException e) {
			LOGGER.info(e.getLocalizedMessage());
		}
	}
}
