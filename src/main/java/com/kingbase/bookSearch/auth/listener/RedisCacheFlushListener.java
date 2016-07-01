package com.kingbase.bookSearch.auth.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.kingbase.bookSearch.common.utils.SpringContextUtil;
import com.kingbase.bookSearch.core.jedis.JedisClient;

/**
 * 当单服务器时候 可以使用这种方式清除redis缓存；但是当搭建多个服务器的时候，使用这种方式会清空了其他服务器的缓存
 * 项目关闭的时候，清空redis缓存数据
 * @author ganliang
 *
 */
public class RedisCacheFlushListener implements ServletContextListener {

	private static final Logger log = Logger.getLogger(RedisCacheFlushListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		log.info("contextDestroyed...................");
		log.info("清空redis缓存..................");
		JedisClient jedisClient=(JedisClient) SpringContextUtil.getBean("jedisClient");
		jedisClient.flushDB();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("contextInitialized...................");
	}

}
