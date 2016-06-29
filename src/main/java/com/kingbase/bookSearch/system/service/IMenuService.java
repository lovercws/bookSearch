package com.kingbase.bookSearch.system.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingbase.bookSearch.system.bean.Menu;

/**
 * 菜单接口
 * @author ganliang
 */
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public interface IMenuService {

	/**
	 * 找到所有的菜单
	 * @return
	 */
	public List<Menu> getAllMenu();

	/**
	 * 保存菜单
	 * @param menuBean 菜单实体
	 */
	@Transactional(readOnly=false)
	public void saveMenu(Menu menuBean);

	/**
	 * 删除菜单
	 * @param id 菜单id
	 */
	@Transactional(readOnly=false)
	public void deleteMenu(Menu menuBean);

	/**
	 * 删除所有的菜单
	 */
	@Transactional(readOnly=false)
	public void deleteAllMenu();

	/**
	 * 批量添加菜单
	 * @param data 
	 */
	@Transactional(readOnly=false)
	public void addBatchFromExcel(List<List<Object>> data);

	/**
	 * 导出菜单
	 * @param menus 菜单集合
	 * @return
	 * @throws IOException 
	 */
	public Workbook exportMenu(List<Menu> menus) throws IOException;

	/**
	 * 获取所有的权限列表
	 * @return
	 */
	public List<Menu> getAllAuth();

	/**
	 * 获取所有的菜单
	 * @return
	 */
	public List<Menu> getAll();

	/**
	 * 获取所有可见的菜单
	 * @param userId 
	 * @return
	 */
	public List<Menu> getAllVisibleMenu(int userId);

	/**
	 * 获取所有可见的菜单
	 * @return
	 */
	public List<Menu> getAllVisibleMenu();
}
