package com.kingbase.bookSearch.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingbase.bookSearch.system.bean.Role;
import com.kingbase.bookSearch.system.bean.UserRole;
import com.kingbase.bookSearch.system.dao.IUserRoleDao;
import com.kingbase.bookSearch.system.dao.impl.UserRoleDaoImpl;
import com.kingbase.bookSearch.system.service.IUserRoleService;

/**
 * 用户角色关系
 * @author ganliang
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService{

	@Resource
	private IUserRoleDao userRoleDao=new UserRoleDaoImpl();

	@Override
	public List<Role> getSelectedRole(int userId) {
		List<Role> roleList=userRoleDao.getSelectedRole(userId);
		for (Role role : roleList) {
			role.setUsers(null);
			role.setMenus(null);
		}
		return roleList;
	}

	@Override
	public List<Role> getUnSelectedRole(int userId) {
		List<Role> roleList=userRoleDao.getUnSelectedRole(userId);
		for (Role role : roleList) {
			role.setMenus(null);
			role.setUsers(null);
		}
		return roleList;
	}

	@Override
	public void saveUserRoles(List<UserRole> userRoles) {
		userRoleDao.saveAll(userRoles);
	}

	@Override
	public void deleteUserRoles(int userId, String roleIds) {
		userRoleDao.deleteUserRoles(userId,roleIds);
	}

	@Override
	public List<String> getRolePermissions(int userId) {
		return userRoleDao.getRolePermissions(userId);
	}

}
