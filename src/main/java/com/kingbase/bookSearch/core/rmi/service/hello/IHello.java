package com.kingbase.bookSearch.core.rmi.service.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicInteger;

public interface IHello extends Remote{

	static final AtomicInteger count=new AtomicInteger(1);
	
	/**
     * 简单的返回“Hello World！"字样
     * @return 返回“Hello World！"字样
     * @throws java.rmi.RemoteException
     */
    public String helloWorld() throws RemoteException;

    /**
     * 一个简单的业务方法，根据传入的人名返回相应的问候语
     * @param someBodyName  人名
     * @return 返回相应的问候语
     * @throws java.rmi.RemoteException
     */
    public String sayHelloToSomeBody(String someBodyName) throws RemoteException; 
    
    public int add() throws RemoteException;
    
    public int get() throws RemoteException;
}
