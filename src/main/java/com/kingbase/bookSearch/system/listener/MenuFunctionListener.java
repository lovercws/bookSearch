package com.kingbase.bookSearch.system.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/**
 * 初始化 SQL 函数 getMenuChildren
 * @author ganliang
 */
@WebListener
public class MenuFunctionListener implements ServletContextListener{

	private static final Logger log=Logger.getLogger(MenuFunctionListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	}

}
