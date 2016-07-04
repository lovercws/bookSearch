package com.kingbase.bookSearch.common.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 读取properties文件的工具类
 */
public class PropertiesUtil {
	private static final String USER_DIR = "user.dir";// 项目路径
	private static final String PATH = "Path";// 项目路径

	private static final String WINDOWS_SYSTEM = "windows_system";// window系统
	private static final String LINUX_SYSTEM = "linux_system";// linux系统

	public Properties load(String fileName) {
		Resource res = new ClassPathResource(fileName);
		Properties p = new Properties();
		try {
			p.load(res.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * 获取项目路径
	 * 
	 * @return
	 */
	public static String getUserDir() {
		return System.getProperty(USER_DIR);
	}

	/**
	 * 获取系统类型
	 * 
	 * @return
	 */
	public static String getSystem() {
		Properties properties = System.getProperties();
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}

		String system = null;
		String osName = System.getProperty("os.name");
		if (osName.contains("Windows")) {
			system = WINDOWS_SYSTEM;
		} else {
			system = LINUX_SYSTEM;
		}
		return system;
	}

	public static String getPath() {
		return System.getenv(PATH);
	}

	public static void main(String[] args) {
		Map<String, String> map = System.getenv();
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}