package com.kingbase.bookSearch.system.dao;

import java.util.List;

import com.kingbase.bookSearch.common.dao.IHibernateDao;
import com.kingbase.bookSearch.system.bean.RoleMenu;

public interface IRoleMenuDao extends IHibernateDao<RoleMenu>{

	public List<RoleMenu> getMenuByRoleId(int roleId);

	public void deleteMenuByRoleId(int roleId);


}
