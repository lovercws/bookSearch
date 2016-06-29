package com.kingbase.bookSearch.system.bean;

import java.io.Serializable;

/**
 * 用户 角色 中间表
 * @author ganliang
 *
 */
public class UserRole implements Serializable{

	private static final long serialVersionUID = 5094859316617575547L;

	private int userId;//用户id
	private int roleId;//角色id
	
	
	public UserRole() {
		super();
	}
	
	public UserRole(int userId, int roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "UserRole [userId=" + userId + ", roleId=" + roleId + "]";
	}
	
	/**
	 * 非持久化对象
	 */
	private String roleIds;
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}
