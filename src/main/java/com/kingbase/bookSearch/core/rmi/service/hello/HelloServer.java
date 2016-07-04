package com.kingbase.bookSearch.core.rmi.service.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.kingbase.bookSearch.core.rmi.service.RmiService;
import com.kingbase.bookSearch.core.rmi.zookeeper.ServiceConsumer;
import com.kingbase.bookSearch.core.rmi.zookeeper.ServiceProvider;

/**
 * 服务发布
 * @author ganliang
 */
public class HelloServer extends RmiService{

	/**
	 * 发布服务
	 * @throws RemoteException
	 */
	public void publishService() throws RemoteException {
		ServiceProvider provider = new ServiceProvider();
		HelloImpl helloService = new HelloImpl();
		provider.publish(helloService, this);
	}

	/**
	 * 获取服务
	 * @return
	 */
	public Remote getService() {
		ServiceConsumer serviceConsumer = new ServiceConsumer(this);
		return serviceConsumer.lookup();
	}

	public void clean() {
		
	}
}
