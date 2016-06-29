package com.kingbase.bookSearch.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingbase.bookSearch.system.bean.RoleMenu;

/**
 * 角色接口
 * @author ganliang
 */
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public interface IRoleMenuService {

	/**
	 * 保存角色和权限之间的关系
	 * @param list
	 */
	@Transactional(readOnly=false)
	public void saveRoleMenu(List<RoleMenu> list,int roleId);

	/**
	 * 获取角色的全部菜单、权限
	 * @param roleId 角色id
	 * @return 
	 */
	public List<RoleMenu> getMenuByRoleId(int roleId);

	/**
	 * 删除角色下的所有权限
	 * @param roleId 角色id
	 */
	public void deleteRoleMenuByRoleId(int roleId);

}
