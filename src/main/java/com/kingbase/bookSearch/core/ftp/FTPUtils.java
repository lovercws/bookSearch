package com.kingbase.bookSearch.core.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @category ftp工具类
 * @author qyan
 * 
 */
public class FTPUtils {

	public static boolean Download(String url,String remotePath,String localPath,String fileName){
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("utf-8");
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		conf.setServerLanguageCode("UTF-8");
		
		try {
			int reply;
			ftp.connect(url);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return true;
	}
	/**
	 * @category FTP下载工具类
	 * @param url 远程IP
	 * @param port端口号
	 * @param username 用户名 任意用户 anonymous
	 * @param password密码
	 * @param remotePath远程路径
	 * @param localPath保留的本地路径
	 * @param fileName 文件名
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static boolean Download(String url, int port, String username, String password, List remotePath,
			String localPath, String fileName, HttpServletResponse response) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("utf-8");
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		conf.setServerLanguageCode("UTF-8");
		try {
			int reply;
			ftp.connect(url, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			boolean f = ftp.changeWorkingDirectory("ftp");
			if (!f) {
				ftp.makeDirectory("ftp");
				ftp.changeWorkingDirectory("ftp");
			}

			boolean isEntered = false;
			for (int i = 0; i < remotePath.size(); i++) {

				isEntered = ftp.changeWorkingDirectory(remotePath.get(i).toString());// 转移到FTP服务器目录

			}

			success = true;
			FTPFile[] fs = ftp.listFiles();
			OutputStream os = response.getOutputStream();
			for (FTPFile ff : fs) {
				if (fileName.equals(ff.getName())) {
					ftp.retrieveFile(ff.getName(), os);
					os.flush();
					os.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @Version1.0
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean uploadFile(String url, int port, String username, String password, List path, String filename,
			InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		// 改变编码格式否则不能正确上传以中文命名的文件
		ftp.setControlEncoding("utf-8");
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		conf.setServerLanguageCode("UTF-8");

		// File file = new File(path);
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 如果返回230表示登录成功
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			boolean fs = ftp.changeWorkingDirectory("ftp");
			if (!fs) {
				ftp.makeDirectory("ftp");
				ftp.changeWorkingDirectory("ftp");
			}
			for (int i = 0; i < path.size(); i++) {
				boolean f = ftp.changeWorkingDirectory(path.get(i).toString());
				if (!f) {
					ftp.makeDirectory(path.get(i).toString());
					ftp.changeWorkingDirectory(path.get(i).toString());
				} else {
					continue;
				}
			}
			// 如果缺少该语句传输txt正常但图片和其他格式的文件出现乱码
			ftp.setFileType(FTP.BINARY_FILE_TYPE);

			ftp.enterLocalPassiveMode();
			boolean s = ftp.storeFile(filename, input);
			if (s) {
				success = true;
			} else {
				success = false;
			}
			input.close();
			ftp.logout();
			return success;
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
			return success;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}

	}

	/**
	 * 
	 * @param url
	 *            FTP服务器
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param input
	 *            输入流
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean FtpMakeDir(String url, int port, String username, String password, List path,
			InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		// 改变编码格式否则不能正确上传以中文命名的文件
		ftp.setControlEncoding("utf-8");
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		conf.setServerLanguageCode("UTF-8");

		try {

			int reply;
			// 连接FTP服务器
			ftp.connect(url, port);

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 如果返回230表示登录成功
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory("ftp");
			for (int i = 0; i < path.size(); i++) {
				boolean f = ftp.changeWorkingDirectory(path.get(i).toString());
				if (!f) {
					ftp.makeDirectory(path.get(i).toString());
					ftp.changeWorkingDirectory(path.get(i).toString());
				}
			}
			success = true;
			input.close();
			ftp.logout();
			return success;
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
			return success;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}

	}

}
