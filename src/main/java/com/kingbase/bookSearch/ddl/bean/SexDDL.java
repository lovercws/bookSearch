package com.kingbase.bookSearch.ddl.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 性别
 * @author ganliang
 */
public class SexDDL implements Serializable {

	private static final long serialVersionUID = 3587974015199217341L;

	private int sexId;// ddl主键
	private String sexCode;// ddl关键码
	private String sexName;// ddl名称
	private String sexRemark;// ddl描述
	private boolean sexActive;// 是否 可用、激活
	private Date startDate;// 创建日期
	private Date limitDate;// 禁用日期

	public SexDDL() {
		super();
	}

	public SexDDL(int sexId, String sexCode, String sexName, String sexRemark, boolean sexActive) {
		super();
		this.sexId = sexId;
		this.sexCode = sexCode;
		this.sexName = sexName;
		this.sexRemark = sexRemark;
		this.sexActive = sexActive;
	}

	public int getSexId() {
		return sexId;
	}

	public void setSexId(int sexId) {
		this.sexId = sexId;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getSexRemark() {
		return sexRemark;
	}

	public void setSexRemark(String sexRemark) {
		this.sexRemark = sexRemark;
	}

	public boolean isSexActive() {
		return sexActive;
	}

	public void setSexActive(boolean sexActive) {
		this.sexActive = sexActive;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	
	@Override
	public String toString() {
		return "SexDDL [sexId=" + sexId + ", sexCode=" + sexCode + ", sexName=" + sexName + ", sexRemark=" + sexRemark
				+ ", sexActive=" + sexActive + ", startDate=" + startDate + ", limitDate=" + limitDate + ", data="
				+ data + "]";
	}

	/**
	 * 非持久化数据
	 */
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
