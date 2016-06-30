package com.kingbase.bookSearch.system.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.core.excel.ExcelUtil;
import com.kingbase.bookSearch.core.tree.BaseTree;
import com.kingbase.bookSearch.core.tree.MenuTree;
import com.kingbase.bookSearch.core.tree.Node;
import com.kingbase.bookSearch.system.bean.Menu;
import com.kingbase.bookSearch.system.service.IMenuService;
import com.kingbase.bookSearch.system.service.impl.MenuServiceImpl;

@Scope("prototype")
@Controller("MenuManagerAction")
public class MenuManagerAction extends BaseAction<Menu>{

	private static final long serialVersionUID = -4818437369420152646L;
	private static final Logger log=Logger.getLogger(MenuManagerAction.class);
	
	private Menu menuBean=this.getModel();
	//Gson gson=new Gson();
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	@Resource
	private IMenuService menuService=new MenuServiceImpl();
	
	/**
	 * 菜单列表
	 * @return
	 */
	public String menuTable(){
		List<Menu> menus = menuService.getAllMenu();
		log.info("菜单列表-->"+menus);
		request.setAttribute("menuList", menus);
		
		String menuComboTree = getMenuComboTree(menus);
		log.info("下拉树菜单-->"+menuComboTree);
		request.setAttribute("menuComboTree", menuComboTree);
		
		return "menuTable";
	}
	
	/**
	 * 获取菜单 树形下拉框
	 * @return
	 * @throws IOException 
	 */
	public String getMenuComboTree(List<Menu> menus){
		List<Node> nodes=new ArrayList<Node>();
		for (Menu menu : menus) {
			nodes.add(new Node(String.valueOf(menu.getId()), String.valueOf(menu.getParentId()), menu.getTitle(), "", ""));
		}
		//MenuTree tree=new MenuTree();
		BaseTree tree=new BaseTree();
		List<Node> treeNodes = tree.parser(nodes);
		String json = gson.toJson(treeNodes);
		json=json.replace(",\"children\":[]", "");
		return json;
	}
	
	/**
	 * 保存菜单
	 * @return
	 */
	public String saveMenu(){
		log.info("保存菜单-->"+menuBean);
		menuBean.setCreateDate(new Date());
		menuBean.setIsmenu(true);
		menuService.saveMenu(menuBean);
		return menuTable();
	}
	
	/**
	 * 更新菜单
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	public void updateMenu() throws IOException{
		log.info("更新菜单-->"+menuBean);
		menuBean.setIsmenu(true);
		menuService.saveMenu(menuBean);
		response.getWriter().print("{success:true}");
	}
	
	/**
	 * 删除一个菜单
	 * @return
	 */
	public String deleteMenu(){
		log.info("删除菜单-->"+menuBean);
		menuService.deleteMenu(menuBean);
		return menuTable();
	}
	
	/**
	 * 删除一个菜单
	 * @return
	 */
	public String deleteTreeMenu(){
		log.info("删除菜单-->"+menuBean);
		menuService.deleteMenu(menuBean);
		return menuTree();
	}
	
	/**
	 * 删除所有的额菜单
	 */
	public String deleteAllMenu(){
		log.info("删除所有的菜单-->");
		menuService.deleteAllMenu();
		return menuTable();
	}
	/**
	 * 菜单树
	 * @return
	 */
	public String menuTree(){
		List<Menu> menus = menuService.getAllMenu();
		
		MenuTree tree=new MenuTree();
		List<Menu> treeNodes = tree.parser(menus);
		String menuComboTree = gson.toJson(treeNodes);
		menuComboTree=menuComboTree.replace(",\"children\":[]", "");		
		log.info("菜单树-->"+menuComboTree);
		
		request.setAttribute("menuComboTree", menuComboTree.replace("\"", "'"));
		return "menuTree";
	}
	
	/**
	 * 获取菜单树
	 * @throws IOException 
	 */
	public void getMenuTree() throws IOException{
		List<Menu> menus = menuService.getAllMenu();
		
		MenuTree tree=new MenuTree();
		List<Menu> treeNodes = tree.parser(menus);
		String menuComboTree = gson.toJson(treeNodes);
		menuComboTree=menuComboTree.replace(",\"children\":[]", "");		
		
		log.info("菜单树-->"+menuComboTree);
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().print(menuComboTree);
	}
	
	/**
	 * 菜单导入
	 * @throws IOException
	 */
	public String importMenu() throws IOException{
		//解析excel
		File file = menuBean.getFile();
		List<List<Object>> data = ExcelUtil.parse(new FileInputStream(file), menuBean.getFileContentType());
		
		//批量生成菜单
		menuService.addBatchFromExcel(data);
		
		return menuTable();
	}
	
	/**
	 * 菜单导出
	 * @throws IOException
	 */
	public void exportMenu() throws IOException{
		//获取所有的菜单
		List<Menu> menus = menuService.getAll();
		
		response.reset();
		// 文件下载
		response.setContentType("application/vnd.ms-excel");
		String filename = "菜单报表（"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ "）";
		
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		response.setHeader("Content-disposition", "attachment;filename="+ filename + ".xls");
		response.setBufferSize(1024);
		
		//获取excel表单
		Workbook workbook=menuService.exportMenu(menus);
		//写入数据
		workbook.write(response.getOutputStream());
		workbook.close();		
	}
	
}
