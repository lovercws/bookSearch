
package com.kingbase.bookSearch.log.bean;

import java.io.Serializable;

/**
 * 书籍上传下载日志
 * @author ganliang
 *
 */
public class BookLog extends Log implements Serializable{

	private static final long serialVersionUID = 4873209976871805214L;

	public static final String BOOK_UPLOAD="1";//书籍上传
	public static final String BOOK_DOWNLOAD="2";//书籍下载
	
	private String bookName;//上传 下载的书籍名称
	private String mode;//是上传 还是下载
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
}
