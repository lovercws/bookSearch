package com.kingbase.bookSearch.system.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色实体
 * 
 * @author ganliang
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -5977909310735033182L;

	private int id;// 角色id
	private String code;// 角色代码
	private String name;// 角色名称

	private String remark;// 角色备注
	private boolean issystem;// 是否是系统内置角色
	private Date createDate;// 角色创建日期

	private Set<User> users = new HashSet<User>();

	private Set<Menu> menus = new HashSet<Menu>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isIssystem() {
		return issystem;
	}

	public void setIssystem(boolean issystem) {
		this.issystem = issystem;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", code=" + code + ", name=" + name + ", remark=" + remark + ", issystem=" + issystem
				+ ", createDate=" + createDate + ", users=" + users + ", menus=" + menus + "]";
	}

	/**
	 * 非持久化字段
	 */
	private List<String> permissions = new ArrayList<String>();

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}
