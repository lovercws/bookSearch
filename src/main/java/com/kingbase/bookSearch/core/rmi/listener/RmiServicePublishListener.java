package com.kingbase.bookSearch.core.rmi.listener;

import java.rmi.RemoteException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.kingbase.bookSearch.common.utils.SpringContextUtil;
import com.kingbase.bookSearch.core.rmi.service.hello.HelloServer;
import com.kingbase.bookSearch.core.rmi.service.hello.IHello;

public class RmiServicePublishListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		HelloServer helloServer = (HelloServer) SpringContextUtil.getBean("helloServer");
		try {
			//发布服务
			helloServer.publishService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		IHello service = (IHello) helloServer.getService();
		try {
			service.sayHelloToSomeBody("cws");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
