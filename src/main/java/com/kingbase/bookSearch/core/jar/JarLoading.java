package com.kingbase.bookSearch.core.jar;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 加载jar
 * @author ganliang
 */
public class JarLoading {

	private static class JarLoader {

		private URLClassLoader urlClassLoader;

		public JarLoader(URLClassLoader urlClassLoader) {
			this.urlClassLoader = urlClassLoader;
		}

		public void loadJar(URL url) throws Exception {
			Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			addURL.setAccessible(true);
			addURL.invoke(urlClassLoader, url);
			System.out.println(url);
		}
	}

	private static void loadjar(JarLoader jarLoader, String path) throws MalformedURLException, Exception {
		File libdir = new File(path);
		//如果是目录 则加载目录下的所有jar
		if (libdir != null && libdir.isDirectory()) {
			//获取jar文件
			File[] listFiles = libdir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					return file.exists() && file.isFile() && file.getName().endsWith(".jar");
				}
			});

			//加载jar
			for (File file : listFiles) {
				jarLoader.loadJar(file.toURI().toURL());
			}
		}
		//加载单个jar
		else if(libdir != null && libdir.isFile()){
			if(libdir.getName().endsWith(".jar")){
				jarLoader.loadJar(libdir.toURI().toURL());
			}
		}
		else {
			System.out.println("[Console Message] Directory [" + path + "] does not exsit, please check it");
			System.exit(0);
		}
	}

	public static void main(String[] args) throws MalformedURLException, Exception {
		JarLoader jarLoader = new JarLoader((URLClassLoader) ClassLoader.getSystemClassLoader());

		loadjar(jarLoader, "E:\\workspace\\kingbaseESB\\kingbaseesb\\WebRoot\\WEB-INF\\lib");

	}
}
