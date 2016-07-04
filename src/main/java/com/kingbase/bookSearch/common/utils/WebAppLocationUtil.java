package com.kingbase.bookSearch.common.utils;

public class WebAppLocationUtil {

	/**
	 * webapp路径
	 */
	public static String WEBAPP_PATH = "";

	/**
	 * 设置路径
	 * @param path
	 */
	public void setWebAppPath(String path) {
		path=path.replace("\\", "/");
		WEBAPP_PATH = path;
	}
}
