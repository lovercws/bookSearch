package com.kingbase.bookSearch.system.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.core.tree.BaseTree;
import com.kingbase.bookSearch.core.tree.Node;
import com.kingbase.bookSearch.core.tree.ZTree;
import com.kingbase.bookSearch.system.bean.Menu;
import com.kingbase.bookSearch.system.bean.RoleMenu;
import com.kingbase.bookSearch.system.service.IMenuService;
import com.kingbase.bookSearch.system.service.IRoleMenuService;
import com.kingbase.bookSearch.system.service.IRoleService;
import com.kingbase.bookSearch.system.service.impl.MenuServiceImpl;
import com.kingbase.bookSearch.system.service.impl.RoleMenuServiceImpl;
import com.kingbase.bookSearch.system.service.impl.RoleServiceImpl;

/**
 * 角色、菜单管理
 * @author ganliang
 */
@Scope("prototype")
@Controller("RoleMenuRelationAction")
public class RoleMenuRelationAction extends BaseAction<RoleMenu>{

	private static final long serialVersionUID = 790622869889474703L;
	private static final Logger log=Logger.getLogger(RoleMenuRelationAction.class);
	
	@Resource
	private IRoleMenuService roleMenuService=new RoleMenuServiceImpl();
	@Resource
	private IRoleService roleService=new RoleServiceImpl();
	@Resource
	private IMenuService menuService=new MenuServiceImpl();
	
	private Gson gson=new Gson();
	private RoleMenu roleMenu=this.getModel();
	
	/**
	 * 对角色分配权限
	 * @throws IOException 
	 */
	public void allocateAuth() throws IOException{
		log.info("分配权限--->"+roleMenu);
		
		List<RoleMenu> list=new ArrayList<RoleMenu>();
		String menuIds = roleMenu.getMenuIds();
		if(menuIds!=null&&!"null".equals(menuIds)){
			String[] strings = menuIds.split(",");
			for (String menuId : strings) {
				list.add(new RoleMenu(roleMenu.getRoleId(), Integer.parseInt(menuId)));
			}
		}
		roleMenuService.saveRoleMenu(list,roleMenu.getRoleId());
		response.getWriter().println("{success:true}");
	}
	
	/**
	 * 获取菜单和权限的树(只获取Node的属性)
	 * @return
	 * @throws IOException 
	 */
	public void getAuthSelectedTree() throws IOException{
		List<Node> nodes=new ArrayList<Node>();
		//获取所有的菜单
		List<Menu> menus = menuService.getAll();
		
		//获取当前角色分配的菜单
		List<RoleMenu> roleMenus = roleMenuService.getMenuByRoleId(roleMenu.getRoleId());
		List<String> menuIds=new ArrayList<String>();
		if(roleMenus.size()>0){
			for (RoleMenu roleMenu : roleMenus) {
				menuIds.add(","+roleMenu.getMenuId()+",");
			}
		}
		//遍历菜单
		for (Menu menu : menus) {
			boolean checked=false;
			if(menuIds.contains(","+menu.getId()+",")){//防止数字包含 48 包含4,8,48
				checked=true;
			}
			nodes.add(new Node(String.valueOf(menu.getId()), String.valueOf(menu.getParentId()), menu.getTitle(), menu.getState(), menu.getSrc(),checked));
		}
		BaseTree tree=new BaseTree();
		List<Node> treeNodes = tree.parser(nodes);
		String menuComboTree = gson.toJson(treeNodes);
		
		menuComboTree=menuComboTree.replace("\"checked\":false,", "");
		menuComboTree=menuComboTree.replace(",\"children\":[]", "");
		log.info("菜单树-->"+menuComboTree);
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().print(menuComboTree);
	}
	
	/**
	 * 获取ztree树
	 * @throws IOException 
	 */
	public String getZTree() throws IOException{
		//获取所有的菜单
		List<Menu> menus = menuService.getAll();
		
		//获取当前角色分配的菜单
		List<RoleMenu> roleMenus = roleMenuService.getMenuByRoleId(roleMenu.getRoleId());
		List<String> menuIds=new ArrayList<String>();
		if(roleMenus.size()>0){
			for (RoleMenu roleMenu : roleMenus) {
				menuIds.add(","+roleMenu.getMenuId()+",");
			}
		}
		List<ZTree> ztrees=new ArrayList<ZTree>();
		//遍历菜单
		for (Menu menu : menus) {
			boolean checked=false;
			if(menuIds.contains(","+menu.getId()+",")){//防止数字包含 48 包含4,8,48
				checked=true;
			}
			ztrees.add(new ZTree(menu.getId(), menu.getParentId(), menu.getTitle(), menu.getSrc(), true, checked));
		}
		String json = gson.toJson(ztrees);
		log.info("角色权限-->>"+json);
		request.setAttribute("permissionMenus", json.replace("\"", "'"));
		return "rolePermission";
	}
}
