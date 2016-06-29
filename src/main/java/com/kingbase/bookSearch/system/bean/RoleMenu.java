package com.kingbase.bookSearch.system.bean;

import java.io.Serializable;

/**
 * 一个角色对应多个权限
 * @author ganliang
 */
public class RoleMenu implements Serializable{

	private static final long serialVersionUID = 8245999147500566191L;
	
	private int roleId;//角色id
	private int menuId;//权限id
	
	public RoleMenu() {
		super();
	}

	public RoleMenu(int roleId, int menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	
	/**
	 * 非持久化对象
	 */
	private String menuIds;

	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	@Override
	public String toString() {
		return "RoleMenu [roleId=" + roleId + ", menuId=" + menuId + ", menuIds=" + menuIds + "]";
	}
	
}
