package com.kingbase.bookSearch.book.bean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * 多文件上传实体
 * @author ganliang
 *
 */
public class MulFileUpload implements Serializable {

	private static final long serialVersionUID = -6181185713687618019L;

	private List<File> file;

	private List<String> fileFileName;

	private List<String> fileContentType;

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

}
