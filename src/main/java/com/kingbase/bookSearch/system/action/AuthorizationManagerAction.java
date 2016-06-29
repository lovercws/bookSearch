package com.kingbase.bookSearch.system.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.core.tree.MenuTree;
import com.kingbase.bookSearch.core.tree.Node;
import com.kingbase.bookSearch.system.bean.Menu;
import com.kingbase.bookSearch.system.service.IMenuService;
import com.kingbase.bookSearch.system.service.impl.MenuServiceImpl;

@Scope("prototype")
@Controller("AuthorizationManagerAction")
/**
 * 权限管理
 * @author ganliang
 */
public class AuthorizationManagerAction extends BaseAction<Menu>{

	private static final long serialVersionUID = -8097811956503091491L;
	private static final Logger log=Logger.getLogger(AuthorizationManagerAction.class);
	
	private Menu authBean=this.getModel();
	//Gson gson=new Gson();
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	@Resource
	private IMenuService menuService=new MenuServiceImpl();
	/**
	 * 获取权限列表
	 * @return
	 */
	public String authTable(){
		List<Menu> allMenus=menuService.getAll();
		
		List<Menu> authList=new ArrayList<Menu>();
		List<Node> nodes=new ArrayList<Node>();
		for (Menu menu : allMenus) {
			if(menu.isIsmenu()){//是菜单
				if(!"".equals(menu.getSrc())){//选择 是功能菜单的 菜单
					nodes.add(new Node(String.valueOf(menu.getId()), String.valueOf(menu.getParentId()), menu.getTitle(), "", ""));
				}
			}else{//是权限
				authList.add(menu);
			}
		}
		
		log.info("权限列表-->"+authList);
		request.setAttribute("authList", authList);
		
		String json = gson.toJson(nodes);
		log.info("权限下拉框-->"+json);
		request.setAttribute("authComboTree", json);
		
		return "authTable";
	}
	
	/**
	 * 保存权限
	 * @return
	 */
	public String saveAuth(){
		log.info("保存权限-->"+authBean);
		authBean.setIsmenu(false);
		authBean.setCreateDate(new Date());
		menuService.saveMenu(authBean);
		return authTable();
	}
	
	/**
	 * 删除权限
	 * @return
	 */
	public String deleteAuth(){
		log.info("删除权限-->"+authBean);
		menuService.deleteMenu(authBean);
		return authTable();
	}
	
	/**
	 * 进入到菜单和权限的树页面
	 * @return
	 */
	public String authTree(){
		return "authTree";
	}
	
	/**
	 * 获取菜单和权限的树
	 * @return
	 * @throws IOException 
	 */
	public void getAuthTree() throws IOException{
		List<Menu> menus = menuService.getAll();
		
		MenuTree tree=new MenuTree();
		List<Menu> treeNodes = tree.parser(menus);
		String menuComboTree = gson.toJson(treeNodes);
		
		log.info("菜单树-->"+menuComboTree);
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().print(menuComboTree);
	}
	
}
