package com.kingbase.bookSearch.core.script;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanIsNotAFactoryException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 执行sql文件
 * @author ganliang
 *
 */
public class SQLScriptRunnerListener implements ServletContextListener {

	private static final Logger log = Logger.getLogger(SQLScriptRunnerListener.class);

	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();

		try {
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			DataSource dataSource=(DataSource) context.getBean("dataSource");
			log.info(dataSource);
			if (dataSource == null) {
				throw new BeanIsNotAFactoryException("dataSource", DataSource.class);
			} else {
				//获取连接
				Connection connection = dataSource.getConnection();
				log.info(connection);
				
				//执行数据备份
			}
		} catch (BeansException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		}
	}

}
