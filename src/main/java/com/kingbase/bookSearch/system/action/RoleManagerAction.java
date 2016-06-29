package com.kingbase.bookSearch.system.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.system.bean.Role;
import com.kingbase.bookSearch.system.service.IRoleMenuService;
import com.kingbase.bookSearch.system.service.IRoleService;
import com.kingbase.bookSearch.system.service.impl.RoleMenuServiceImpl;
import com.kingbase.bookSearch.system.service.impl.RoleServiceImpl;

@Scope("prototype")
@Controller("RoleManagerAction")
public class RoleManagerAction extends BaseAction<Role>{

	private static final long serialVersionUID = -4818437369420152646L;
	private static final Logger log=Logger.getLogger(RoleManagerAction.class);
	
	private Role role=this.getModel();
	
	@Resource
	private IRoleService roleService=new RoleServiceImpl();
	
	@Resource
	private IRoleMenuService roleMenuService=new RoleMenuServiceImpl();
	
	/**
	 * 角色列表
	 * @return
	 */
	public String roleList(){
		//获取所有的角色
		List<Role> roleList=roleService.getAllRole();
		log.info("角色列表-->"+roleList);
		request.setAttribute("roleList", roleList);
		return "roleList";
	}
	
	/**
	 * 保存角色
	 * @return
	 */
	public String saveRole(){
		log.info("保存角色-->"+role);
		role.setCreateDate(new Date());
		roleService.saveRole(role);
		return roleList();
	}
	
	/**
	 * 保存角色
	 * @return
	 */
	public String deleteRole(){
		log.info("删除角色-->"+role);
		roleMenuService.deleteRoleMenuByRoleId(role.getId());
		
		roleService.deleteRole(role);
		return roleList();
	}
	
}
