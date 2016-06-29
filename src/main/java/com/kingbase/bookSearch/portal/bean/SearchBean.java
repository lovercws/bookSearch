package com.kingbase.bookSearch.portal.bean;

import java.io.Serializable;

/**
 * 搜索实体
 * @author ganliang
 */
public class SearchBean implements Serializable{

	private static final long serialVersionUID = -3658766780450733866L;

	private String content;//搜索内容
	
	private String fieldName;//域名

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
}
