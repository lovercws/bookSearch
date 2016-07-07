package com.kingbase.bookSearch.common.bean;

import java.io.File;
import java.io.Serializable;

/**
 * 书单文件上传实体
 * @author ganliang
 *
 */
public class FileUpload implements Serializable{

	private static final long serialVersionUID = -6181185713687618019L;

	//提交过来的文件
	private File file;
    
    //提交过来的file的名字
    private String fileFileName;
    
    //提交过来的file的MIME类型
    private String fileContentType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
    
}
