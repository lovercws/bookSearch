package com.kingbase.bookSearch.system.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.kingbase.bookSearch.common.dao.impl.HibernateDaoImpl;
import com.kingbase.bookSearch.system.bean.Menu;
import com.kingbase.bookSearch.system.dao.IMenuDao;

@Repository
public class MenuDaoImpl extends HibernateDaoImpl<Menu> implements IMenuDao{

	@Override
	public void deleteAllChildren(Menu menuBean) {
		//菜单id
		final int menuId=menuBean.getId();
		
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("DELETE FROM tb_menu WHERE FIND_IN_SET(MENU_ID,getMenuChildren(?))");
				Query query = sqlQuery.setParameter(0, menuId);
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void addAll(List<Menu> menus) {
		Session session = getSession();
		try {
			for (Menu menu : menus) {
				SQLQuery sqlQuery = session.createSQLQuery("INSERT INTO tb_menu(menu_id,parent_menu_id,menu_code,menu_title,menu_order,menu_icon,menu_src,menu_state,menu_auto_show,menu_create_date) VALUES(?,?,?,?,?,?,?,?,?,?)");
				sqlQuery.setParameter(0, menu.getId());
				sqlQuery.setParameter(1, menu.getParentId());
				sqlQuery.setParameter(2, menu.getCode());
				sqlQuery.setParameter(3, menu.getTitle());
				sqlQuery.setParameter(4, menu.getOrder());
				sqlQuery.setParameter(5, menu.getIcon());
				sqlQuery.setParameter(6, menu.getSrc());
				sqlQuery.setParameter(7, menu.getState());
				sqlQuery.setParameter(8, menu.isAutoShow());
				sqlQuery.setParameter(9, menu.getCreateDate());
				sqlQuery.executeUpdate();
			}
		} catch (HibernateException e) {
		}finally{
		}
	}


	public void trancate(){
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery("TRUNCATE TABLE tb_menu");
				query.executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getAllVisibleMenu(final int userId) {
		List<Menu> menus = (List<Menu>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("select m.* from tb_menu m where m.menu_is_menu=true  and m.menu_id in(select rm.menu_id from tb_user_role ur left join tb_role_menu rm on(rm.role_id=ur.role_id) where ur.user_id=?) ORDER BY m.menu_order ASC");
				sqlQuery.addEntity(Menu.class);
				sqlQuery.setParameter(0, userId);
				List<Menu> list = sqlQuery.list();
				return list;
			}
		});
		return menus;
	}

}
