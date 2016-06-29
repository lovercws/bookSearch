package com.kingbase.bookSearch.system.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kingbase.bookSearch.book.bean.FileUpload;

/**
 * 菜单
 * @author ganliang
 */
public class Menu extends FileUpload implements Serializable{

	private static final long serialVersionUID = -5977909310735033182L;

	private int id;//菜单主键id
	private int parentId;//上级菜单id
	private String code;//菜单代号
	private String title;//菜单标题
	
	private int order;//菜单顺序
	private String icon;//菜单图标
	private String src;//菜单链接
	
	private String state;//open closed
	private boolean autoShow;//菜单是否显示
	private Date createDate;//菜单创建日期
	
	private boolean ismenu;//是否是菜单
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isAutoShow() {
		return autoShow;
	}
	public void setAutoShow(boolean autoShow) {
		this.autoShow = autoShow;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isIsmenu() {
		return ismenu;
	}
	public void setIsmenu(boolean ismenu) {
		this.ismenu = ismenu;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", parentId=" + parentId + ", code=" + code + ", title=" + title + ", order=" + order
				+ ", icon=" + icon + ", src=" + src + ", state=" + state + ", autoShow=" + autoShow + ", createDate="
				+ createDate + ", ismenu=" + ismenu + "]";
	}

	/**
	 * 非持久化对象
	 */
	public String parentTitle;//父菜单标题
	public String parentCode;//父菜单代号
	private List<Menu> children = new ArrayList<Menu>(); // 子节点(不重复)

	public String getParentTitle() {
		return parentTitle;
	}
	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
}
