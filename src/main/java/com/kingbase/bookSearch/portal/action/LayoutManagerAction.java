package com.kingbase.bookSearch.portal.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.core.tree.BaseTree;
import com.kingbase.bookSearch.core.tree.Node;
import com.kingbase.bookSearch.system.bean.Menu;
import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.service.IMenuService;
import com.kingbase.bookSearch.system.service.impl.MenuServiceImpl;

@Scope("prototype")
@Controller("LayoutManagerAction")
public class LayoutManagerAction extends BaseAction<User> {

	private static final long serialVersionUID = -8495843222180725461L;
	private static final Logger log=Logger.getLogger(LayoutManagerAction.class);
	
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	@Resource
	private IMenuService menuService = new MenuServiceImpl();

	public String header() {
		return "header";
	}

	/**
	 * 获取菜单栏
	 * @return
	 */
	public String getAllMenu() {
		User user = (User) request.getSession().getAttribute(User.SESSION_USER);
		// 将菜单转化为节点
		List<Node> nodes = new ArrayList<>();
		
		List<Menu> menus=null;
		if(user==null){
			menus=menuService.getAllVisibleMenu();
		}else{
			menus = menuService.getAllVisibleMenu(user.getId());
		}
		
		for (Menu menu : menus) {
			nodes.add(new Node(String.valueOf(menu.getId()), String.valueOf(menu.getParentId()), menu.getTitle(),
					menu.getState(), menu.getSrc()));
		}
		// 解析树
		BaseTree tree = new BaseTree();
		List<Node> list = tree.parser(nodes);

		String json = gson.toJson(list);
		log.info("菜单栏-->" + json);
		request.setAttribute("menuData", json);
		return "getAllMenu";
	}
}
