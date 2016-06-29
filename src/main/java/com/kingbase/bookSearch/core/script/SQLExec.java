package com.kingbase.bookSearch.core.script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;

import com.mchange.v1.db.sql.ConnectionUtils;

/**
 * 执行sql文件
 * @author ganliang
 */
public class SQLExec {

	private static final Logger log = Logger.getLogger(SQLScriptRunnerListener.class);
	
	/**
	 * 执行scriptRunner
	 * @param sqlPath 
	 * @param connection
	 * @throws IOException 
	 */
	public static void exec(String sqlPath,Connection connection) throws IOException{
		ScriptRunner runner=new ScriptRunner(connection);
		try {
			runner.setAutoCommit(false);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			
			runner.runScript(new InputStreamReader(Resources.getResourceAsStream(sqlPath),"UTF-8"));
			
			log.info("sql执行完毕.....");
		} catch (UnsupportedEncodingException e) {
			log.error("UTF-8 is UNSUPPORTED ENCODING",e);
		} catch (FileNotFoundException e) {
			log.error("SQLPATH【"+sqlPath+"】 NOT FOUND",e);
		}catch(IOException e){
			log.error(e);
		}finally{
			runner.closeConnection();
		}
	}
}
