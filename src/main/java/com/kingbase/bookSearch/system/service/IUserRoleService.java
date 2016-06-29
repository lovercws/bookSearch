package com.kingbase.bookSearch.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingbase.bookSearch.system.bean.Role;
import com.kingbase.bookSearch.system.bean.UserRole;

/**
 * 用户角色关系
 * @author ganliang
 *
 */
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public interface IUserRoleService {

	/**
	 * 获取用户选择的角色列表
	 * @param userId 用户id
	 * @return 角色列表
	 */
	public List<Role> getSelectedRole(int userId);

	/**
	 * 获取用户未选择的角色列表
	 * @param userId 用户id
	 * @return 角色列表
	 */
	public List<Role> getUnSelectedRole(int userId);

	/**
	 * 保存用户角色关系
	 * @param userRoles
	 */
	@Transactional(readOnly=false)
	public void saveUserRoles(List<UserRole> userRoles);

	/**
	 * 删除用户角色关系
	 * @param userId
	 * @param roleIds
	 */
	@Transactional(readOnly=false)
	public void deleteUserRoles(int userId, String roleIds);

	/**
	 * 获取用户的角色、角色下的权限列表
	 * @param userId
	 * @return
	 */
	public List<String> getRolePermissions(int userId);

}
