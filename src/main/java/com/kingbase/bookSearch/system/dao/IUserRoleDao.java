package com.kingbase.bookSearch.system.dao;

import java.util.List;

import com.kingbase.bookSearch.common.dao.IHibernateDao;
import com.kingbase.bookSearch.system.bean.Role;
import com.kingbase.bookSearch.system.bean.UserRole;

public interface IUserRoleDao extends IHibernateDao<UserRole>{

	public List<Role> getSelectedRole(int userId);

	public List<Role> getUnSelectedRole(int userId);

	public void deleteUserRoles(int userId, String roleIds);

	public List<String> getRolePermissions(int userId);

}
