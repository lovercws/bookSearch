package com.kingbase.bookSearch.core.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kingbase.bookSearch.system.bean.Menu;

public class MenuTree {
	/**
	 * 生成菜单树
	 * @param Menus 节点集合
	 * @return
	 */
	public List<Menu> parser(List<Menu> Menus){
		if(Menus==null||Menus.size()==0){
			return null;
		}
		
		//获取根节点
		List<Menu> topMenus=getTopMenus(Menus);
		
		//迭代根节点
		Iterator<Menu> iterator = topMenus.iterator();
		while(iterator.hasNext()){
			Menu topMenu = iterator.next();
			iteratorTopMenu(topMenu,Menus);
		}
		
		return topMenus;
	}

	/**
	 * 获取根节点集合
	 * @param Menus 节点集合
	 * @return
	 */
	private List<Menu> getTopMenus(List<Menu> Menus) {
		List<Menu> topMenus=new ArrayList<Menu>();
		//迭代所有的节点
		Iterator<Menu> iterator = Menus.iterator();
		while(iterator.hasNext()){
			Menu menu = iterator.next();
			//获取父节点
			int parentId = menu.getParentId();
			if(parentId==0){
				topMenus.add(menu);
				
				iterator.remove();
			}
		}
		
		return topMenus;
	}
	
	/**
	 * 迭代根节点
	 * @param topMenu 顶节点
	 * @param Menus 所有的节点
	 */
	private void iteratorTopMenu(Menu topMenu, List<Menu> Menus) {
		List<Menu> childrenMenus=getDirectChildrenMenu(topMenu.getId(),Menus);
		for (Menu childrenMenu : childrenMenus) {
			iteratorTopMenu(childrenMenu, Menus);
		}
		topMenu.getChildren().addAll(childrenMenus);       
	}

	/**
	 * 获取直接子节点
	 * @param id 节点id
	 * @param Menus 所有的节点
	 * @return
	 */
	private List<Menu> getDirectChildrenMenu(int id, List<Menu> Menus) {
		List<Menu> directChildrenMenus=new ArrayList<Menu>();
		//迭代所有的节点
		Iterator<Menu> iterator = Menus.iterator();
		while(iterator.hasNext()){
			Menu Menu = iterator.next();
			if(id==Menu.getParentId()){
				directChildrenMenus.add(Menu);
				
				iterator.remove();
			}
		}		
		return directChildrenMenus;
	}
}
