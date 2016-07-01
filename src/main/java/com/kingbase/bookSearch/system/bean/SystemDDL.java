package com.kingbase.bookSearch.system.bean;

import java.io.Serializable;

/**
 * 数据字典
 * 
 * @author ganliang
 */
public class SystemDDL implements Serializable {

	private static final long serialVersionUID = -5939726919628812472L;

	private Integer id; // 主键ID(自增长)
	private String keyword; // 数据类型
	private Integer ddlCode; // 数据项的code
	private String ddlName; // 数据项的value

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getDdlCode() {
		return ddlCode;
	}

	public void setDdlCode(Integer ddlCode) {
		this.ddlCode = ddlCode;
	}

	public String getDdlName() {
		return ddlName;
	}

	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
}
