package com.kingbase.bookSearch.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class HostUtil {

	public static String getHost(HttpServletRequest request,String servletPath) throws UnknownHostException{
		//获取IP
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		
		//获取服务的端口号
		int serverPort = request.getServerPort();
		
		//获取项目路径
		String contextPath = request.getContextPath();
		
		String url="http://"+hostAddress+":"+serverPort+contextPath+"/"+servletPath;
		return url;
	}
}
