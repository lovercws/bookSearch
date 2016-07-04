package com.kingbase.bookSearch.common;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.kingbase.bookSearch.core.rmi.service.hello.IHello;

public class Rmi {
	public static void main(String[] args) {
		try {
			IHello rhello = (IHello) Naming.lookup("rmi://127.0.0.1:9001/com.kingbase.bookSearch.core.rmi.service.hello.HelloImpl");
			int add = rhello.add();
			System.out.println("add--->>>" + add);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}
}
