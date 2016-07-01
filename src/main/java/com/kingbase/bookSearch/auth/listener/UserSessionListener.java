package com.kingbase.bookSearch.auth.listener;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.kingbase.bookSearch.core.jedis.JedisClient;

/**
 * 检测session监听器
 * @author ganliang
 */
public class UserSessionListener implements SessionListener {

	@Resource
	private JedisClient JedisClient;
	
	@Override
	public void onStart(Session session) {
		System.out.println("onStart");
	}

	@Override
	public void onStop(Session session) {
		Collection<Object> attributeKeys = session.getAttributeKeys();
		for (Object object : attributeKeys) {
			System.out.println(object);
		}
		System.out.println("onStop");
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("onExpiration");
	}

}
