package com.kingbase.bookSearch.system.dao;

import java.util.List;

import com.kingbase.bookSearch.common.dao.IHibernateDao;
import com.kingbase.bookSearch.system.bean.Menu;

public interface IMenuDao extends IHibernateDao<Menu>{

	/**
	 * 递归删除所有的子节点
	 * @param menuBean
	 */
	public void deleteAllChildren(Menu menuBean);

	/**
	 * 保存菜单（自定义id）
	 * @param menus 菜单集合
	 */
	public void addAll(List<Menu> menus);

	/**
	 * 截断表
	 */
	public void trancate();

	/**
	 * 获取当前用户的权限
	 * @param userId
	 * @return
	 */
	public List<Menu> getAllVisibleMenu(int userId);
}
