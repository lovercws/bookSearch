package com.kingbase.bookSearch.system.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.system.bean.Role;
import com.kingbase.bookSearch.system.bean.UserRole;
import com.kingbase.bookSearch.system.service.IRoleService;
import com.kingbase.bookSearch.system.service.IUserRoleService;
import com.kingbase.bookSearch.system.service.impl.RoleServiceImpl;
import com.kingbase.bookSearch.system.service.impl.UserRoleServiceImpl;

/**
 * 角色、菜单管理
 * @author ganliang
 */
@Scope("prototype")
@Controller("UserRoleRelationAction")
public class UserRoleRelationAction extends BaseAction<UserRole>{

	private static final long serialVersionUID = 790622869889474703L;
	private static final Logger log=Logger.getLogger(UserRoleRelationAction.class);
	
	private Gson gson=new Gson();
	private UserRole userRole=this.getModel();
	
	@Resource
	private IUserRoleService userRoleService=new UserRoleServiceImpl();
	
	@Resource
	private IRoleService roleService=new RoleServiceImpl();
	
	/**
	 * 获取用户选择的角色列表
	 * @throws IOException 
	 */
	@ResponseBody
	public void getUserRole() throws IOException{
		int userId = userRole.getUserId();
		
		//获取当前用户选择的角色
		List<Role> selectedRoleList = userRoleService.getSelectedRole(userId);
		
		//获取当前用户未选择的角色
		List<Role> unSelectedRoleList=userRoleService.getUnSelectedRole(userId);
		
		String json="{'selectedRoleList':"+gson.toJson(selectedRoleList)+",'unSelectedRoleList':"+gson.toJson(unSelectedRoleList)+"}";
		log.info("角色列表-->"+json);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json);
	}
	
	/**
	 * 对用户添加角色
	 * @throws IOException 
	 */
	public void addRoleToUser() throws IOException{
		List<UserRole> userRoles=new ArrayList<UserRole>();
		
		String roleIds = userRole.getRoleIds();
		int userId = userRole.getUserId();
		if(roleIds!=null){
			String[] roles = roleIds.split(",");
			for (String string : roles) {
				userRoles.add(new UserRole(userId, Integer.parseInt(string)));
			}
		}
		log.info("添加用户角色-->"+userRole);
		//保存
		userRoleService.saveUserRoles(userRoles);
		
		getUserRole();
	}
	
	/**
	 * 从用户删除角色
	 * @throws IOException 
	 */
	public void deleteRoleFromUser() throws IOException{
		userRoleService.deleteUserRoles(userRole.getUserId(),userRole.getRoleIds());
		log.info("删除用户角色-->"+userRole);
		getUserRole();
	}
}
