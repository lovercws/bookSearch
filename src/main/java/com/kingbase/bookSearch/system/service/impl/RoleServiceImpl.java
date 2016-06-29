package com.kingbase.bookSearch.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kingbase.bookSearch.system.bean.Role;
import com.kingbase.bookSearch.system.dao.IRoleDao;
import com.kingbase.bookSearch.system.dao.impl.RoleDaoImpl;
import com.kingbase.bookSearch.system.service.IRoleService;

/**
 * 角色实现类
 * @author ganliang
 */
@Service
public class RoleServiceImpl implements IRoleService{

	private static final Logger log=Logger.getLogger(RoleServiceImpl.class);

	@Resource
	private IRoleDao roleDao=new RoleDaoImpl();
	
	@Override
	public List<Role> getAllRole() {
		List<Role> roleList = roleDao.findAll();
		for (Role role : roleList) {
			role.setUsers(null);
			role.setMenus(null);
		}
		return roleList;
	}

	@Override
	public void saveRole(Role role) {
		roleDao.saveOrUpdate(role);
	}

	@Override
	public void deleteRole(Role role) {
		//删除角色
		roleDao.delete(role);
	}
}
