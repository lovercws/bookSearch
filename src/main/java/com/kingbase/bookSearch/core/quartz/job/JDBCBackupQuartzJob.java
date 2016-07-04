package com.kingbase.bookSearch.core.quartz.job;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.kingbase.bookSearch.common.utils.WebAppLocationUtil;

/**
 * 数据库定时备份任务
 * 
 * @author ganliang
 */
public class JDBCBackupQuartzJob extends QuartzJobBean {

	private String username;
	private String password;
	private String database;
	private String host;
	private int port;
	private String backupPath;
	private String binPath;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Logger log = Logger.getLogger(JDBCBackupQuartzJob.class);

	@Override
	protected void executeInternal(JobExecutionContext jobDataMap) throws JobExecutionException {
		log.info("数据库备份................................");
		// 获取数据库的配置参数
		initJDBCParameters(jobDataMap);

		// 组装备份sql
		String backupSql = initBackupSQL();
		log.info("备份语句" + backupSql);

		// 执行备份
		executeBackupSQL(backupSql);
	}

	/**
	 * 从配置中 获取数据库的参数
	 * 
	 * @param jobDataMap
	 */
	private void initJDBCParameters(JobExecutionContext context) {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		Object usernameObj = jobDataMap.get("username");
		Object passwordObj = jobDataMap.get("password");
		Object databaseObj = jobDataMap.get("database");
		if (usernameObj == null || passwordObj == null || databaseObj == null) {
			throw new IllegalArgumentException();
		}
		username = usernameObj.toString();
		password = passwordObj.toString();
		database = databaseObj.toString();

		// 默认主机为本地主机
		Object hostObj = jobDataMap.get("host");
		if (hostObj == null) {
			host = "127.0.0.1";
		} else {
			host = hostObj.toString();
		}

		// 端口号默认位3306
		Object portObj = jobDataMap.get("port");
		if (portObj == null) {
			port = 3306;
		} else {
			port = Integer.parseInt(portObj.toString());
		}

		// 备份路径;默认为用户路径；相对路径为项目路径；绝对路径为磁盘路径
		Object backupPathObj = jobDataMap.get("backupPath");
		if (backupPathObj == null) {
			throw new IllegalArgumentException();
		}
		backupPath = backupPathObj.toString();
		// 如果配置的是相对路径 则获取项目的相对路径
		if (!new File(backupPath).exists()) {
			backupPath = WebAppLocationUtil.WEBAPP_PATH + "/" + backupPath;
			File backupFile = new File(backupPath);
			if (!backupFile.exists()) {
				backupFile.mkdirs();
			}
		}
		backupPath = backupPath + database + "_" + sdf.format(new Date()) + ".sql";

		// mysql二进制文件；window系统、linux系统
		Object binPathObj = jobDataMap.get("binPath");
		if (binPathObj == null || "".equals(binPathObj.toString())) {
			throw new IllegalArgumentException();
		}
		binPath = binPathObj.toString();
		if (!new File(binPath).exists()) {
			binPath = WebAppLocationUtil.WEBAPP_PATH + "/" + binPath;
			File binFile = new File(binPath);
			if (!binFile.exists()) {
				binFile.mkdirs();
			}
		}
	}

	/**
	 * 组装备份sql
	 * 
	 * @return
	 */
	private String initBackupSQL() {
		StringBuilder builder = new StringBuilder();
		builder.append(binPath + "mysqldump ");

		builder.append("-u" + username + " -p" + password + " -h" + host + " -P" + port + " " + database);

		builder.append(" >" + backupPath);

		return builder.toString();
	}

	/**
	 * 执行备份sql
	 * 
	 * @param backupSql
	 */
	private void executeBackupSQL(String backupSql) {
		try {
			StringBuilder cmd = new StringBuilder();
			String osName = System.getProperty("os.name");
			if (osName.startsWith("Windows")) {
				if (osName.equals("Windows 95")) { // windows 95 only
					cmd.append("command.com");
				} else {
					cmd.append("cmd.exe");
				}
				cmd.append(" /C");
			} else if (osName.equals("Linux")) {
				cmd.append("/bin/sh -c ");
			} else {
				throw new IllegalArgumentException();
			}
			Runtime.getRuntime().exec(cmd.toString() + backupSql);
			log.info("备份成功" + backupPath);
		} catch (IOException e) {
			log.error("备份失败", e);
		}
	}

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec(
					" cmd /c c://mysqldump -uroot -plovecws -h127.0.0.1 -P3305 bookSearch >c:/sql/backup/bookSearch_20160704104300.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
