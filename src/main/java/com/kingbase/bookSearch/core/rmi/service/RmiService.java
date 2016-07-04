package com.kingbase.bookSearch.core.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * rmi服务
 * @author ganliang
 */
public abstract class RmiService {
	private String zookeeperURL;// zookeeper 服务器地址
	private int zookeeperTimeout;// zookeeper timeout
	private String zookeeperPath;// zookeeper路径

	public String serviceHost;// 服务地址
	public int servicePort;
	public String servicePath;

	public String getZookeeperURL() {
		return zookeeperURL;
	}

	public void setZookeeperURL(String zookeeperURL) {
		this.zookeeperURL = zookeeperURL;
	}

	public int getZookeeperTimeout() {
		return zookeeperTimeout;
	}

	public void setZookeeperTimeout(int zookeeperTimeout) {
		this.zookeeperTimeout = zookeeperTimeout;
	}

	public String getZookeeperPath() {
		return zookeeperPath;
	}

	public void setZookeeperPath(String zookeeperPath) {
		this.zookeeperPath = zookeeperPath;
	}

	public String getServiceHost() {
		return serviceHost;
	}

	public void setServiceHost(String serviceHost) {
		this.serviceHost = serviceHost;
	}

	public int getServicePort() {
		return servicePort;
	}

	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}

	public String getServicePath() {
		return servicePath;
	}

	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}

	@Override
	public String toString() {
		return "RmiService [zookeeperURL=" + zookeeperURL + ", zookeeperTimeout=" + zookeeperTimeout
				+ ", zookeeperPath=" + zookeeperPath + ", serviceHost=" + serviceHost + ", servicePort=" + servicePort
				+ ", servicePath=" + servicePath + "]";
	}
	
	/**
	 * 发布服务
	 * @throws RemoteException 
	 */
	public abstract void publishService() throws RemoteException;

	/**
	 * 获取注册的服务
	 */
	public abstract Remote getService();

	/**
	 * 系统关闭的时候 清理资源 rmi、zookeeper
	 */
	public abstract void clean();
}
