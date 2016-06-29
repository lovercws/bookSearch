package com.kingbase.bookSearch.log.bean;

import java.util.Date;

public class Log {

	private int id;//主键
	private int userId;//访问的用户id 
	private String userName;//访问的用户名称
	
	private String url;//访问url
	private String moduleName;//模块名称
	
	private String ipAddress;//ip地址
	private String method;//访问的方法
	private String params;//参数
	
	private Date date;//访问日期
}
