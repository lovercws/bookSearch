package com.kingbase.bookSearch.system.service.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.kingbase.bookSearch.core.excel.ExcelUtil;
import com.kingbase.bookSearch.system.bean.Menu;
import com.kingbase.bookSearch.system.dao.IMenuDao;
import com.kingbase.bookSearch.system.dao.impl.MenuDaoImpl;
import com.kingbase.bookSearch.system.service.IMenuService;

/**
 * 菜单实现类
 * @author ganliang
 */
@Service
public class MenuServiceImpl implements IMenuService{

	private static final Logger log=Logger.getLogger(MenuServiceImpl.class);
	
	@Resource
	private IMenuDao menuDao=new MenuDaoImpl();
	
	public List<Menu> getAllMenu(){
		List<Menu> menus = menuDao.find("FROM Menu where ismenu=true ORDER BY order ASC");
		for (Menu menu : menus) {
			int parentId = menu.getParentId();
			//存在父菜单
			if(parentId!=0){
				for (Menu parentMenu : menus) {
					if(parentMenu.getId()==parentId){
						menu.setParentTitle(parentMenu.getTitle());
						menu.setParentCode(parentMenu.getCode());
						break;
					}
				}
			}
		}
		return menus;
	}
	
	public List<Menu> getAllVisibleMenu(){
		List<Menu> menus=menuDao.find("FROM Menu where ismenu=true");
		return menus;
	}
	
	public List<Menu> getAllVisibleMenu(int userId){
		List<Menu> menus=menuDao.getAllVisibleMenu(userId);
		return menus;
	}

	@Override
	public void saveMenu(Menu menuBean) {
		menuDao.saveOrUpdate(menuBean);
	}

	@Override
	public void deleteMenu(Menu menuBean) {
		menuDao.deleteAllChildren(menuBean);
	}

	@Override
	public void deleteAllMenu() {
		menuDao.trancate();
	}

	@Override
	public void addBatchFromExcel(List<List<Object>> data) {
		List<Menu> menus=new ArrayList<Menu>();
		if(data==null||data.size()==0){
			log.error("解析Excel数据为空。");
			return;
		}
		//获取excel标题行
		List<Object> headers = data.get(0);
		try {
			//遍历多行数据
			for (int i = 1; i < data.size(); i++) {
				Menu menu=new Menu();
				
				List<Object> list = data.get(i);
				//遍历一行数据的多列
				for (int j = 0; j < list.size(); j++) {
					Object headerValue = headers.get(j);
					Object colValue = list.get(j);
					if(headerValue!=null&&colValue!=null){
						Field field = FieldUtils.getDeclaredField(menu.getClass(), String.valueOf(headerValue), true);
						field.setAccessible(true);
						String value=colValue.toString();
						String type = field.getType().getName();
						log.info("fieldName="+headerValue+" type="+type+"  value="+value);
						switch (type) {
						case "boolean":
							colValue=Boolean.valueOf(value);
							break;
						case "byte":
							colValue=Byte.valueOf(value);
							break;
						case "short":
							colValue=Short.valueOf(value);
							break;
						case "int":
							Float floatId=Float.parseFloat(value);
							colValue=floatId.intValue();
							break;
						case "float":
							colValue=Float.parseFloat(value);
							break;
						case "double":
							colValue=Double.parseDouble(value);
							break;
						case "long":
							colValue=Long.parseLong(value);
							break;
						case "java.util.Date":
							colValue=DateUtils.parseDate(value, "yyy-MM-dd HH:mm:ss");
							break;
						default:
							break;
						}
						field.set(menu, colValue);
					}
				}
				menus.add(menu);
			}
			
			//保存菜单
			menuDao.addAll(menus);
		} catch (IllegalArgumentException e) {
			log.error(e);
		}catch(ParseException e){
			log.error(e);
		}catch(IllegalAccessException e){
			log.error(e);
		}
	}

	@Override
	public Workbook exportMenu(List<Menu> menus) throws IOException {
		if(menus==null||menus.size()==0){
			return null;
		}
		//组装excel头
		List<String> headers=new ArrayList<String>();
		headers.add("id");headers.add("parentId");headers.add("code");
		headers.add("title");headers.add("order");headers.add("icon");
		headers.add("src");headers.add("state");headers.add("autoShow");
		headers.add("createDate");
		
		//组装excel体
		List<List<Object>> data=new ArrayList<List<Object>>();
		for (Menu menu : menus) {
			List<Object> body=new ArrayList<Object>();
			body.add(menu.getId());body.add(menu.getParentId());body.add(menu.getCode());
			body.add(menu.getTitle());body.add(menu.getOrder());body.add(menu.getIcon());
			body.add(menu.getSrc());body.add(menu.getState());body.add(menu.isAutoShow());
			body.add(menu.getCreateDate());
			data.add(body);
		}
		//生成excel表单
		return ExcelUtil.createWorkbook("菜单", headers, data, ExcelUtil.EXCEL_XLS);
	}

	@Override
	public List<Menu> getAllAuth() {
		List<Menu> menus = menuDao.find("FROM Menu where autoShow=true AND ismenu=false ORDER BY order ASC");
		return menus;
	}

	@Override
	public List<Menu> getAll() {
		List<Menu> menus = menuDao.find("FROM Menu ORDER BY order ASC");
		for (Menu menu : menus) {
			int parentId = menu.getParentId();
			//存在父菜单
			if(parentId!=0){
				for (Menu parentMenu : menus) {
					if(parentMenu.getId()==parentId){
						menu.setParentTitle(parentMenu.getTitle());
						menu.setParentCode(parentMenu.getCode());
						break;
					}
				}
			}
		}
		return menus;
	}
}
