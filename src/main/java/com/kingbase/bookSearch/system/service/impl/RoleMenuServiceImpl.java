package com.kingbase.bookSearch.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kingbase.bookSearch.system.bean.RoleMenu;
import com.kingbase.bookSearch.system.dao.IRoleMenuDao;
import com.kingbase.bookSearch.system.dao.impl.RoleMenuDaoImpl;
import com.kingbase.bookSearch.system.service.IRoleMenuService;

/**
 * 角色实现类
 * @author ganliang
 */
@Service
public class RoleMenuServiceImpl implements IRoleMenuService{

	private static final Logger log=Logger.getLogger(RoleMenuServiceImpl.class);

	@Resource
	private IRoleMenuDao roleMenuDao=new RoleMenuDaoImpl();
	
	@Override
	public void saveRoleMenu(List<RoleMenu> list,int roleId) {
		//删除该角色的所有权限
		roleMenuDao.deleteMenuByRoleId(roleId);
		
		//保存关于 该角色的权限
		if(list.size()>0){
			roleMenuDao.saveAll(list);
		}
	}

	@Override
	public List<RoleMenu> getMenuByRoleId(int roleId) {
		return roleMenuDao.getMenuByRoleId(roleId);
	}

	@Override
	public void deleteRoleMenuByRoleId(int roleId) {
		roleMenuDao.deleteByHql("roleId="+roleId);
	}
}
