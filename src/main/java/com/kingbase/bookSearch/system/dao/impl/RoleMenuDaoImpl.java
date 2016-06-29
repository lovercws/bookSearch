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
import com.kingbase.bookSearch.system.bean.RoleMenu;
import com.kingbase.bookSearch.system.dao.IRoleMenuDao;

@Repository
public class RoleMenuDaoImpl  extends HibernateDaoImpl<RoleMenu> implements IRoleMenuDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleMenu> getMenuByRoleId(final int roleId) {
		return (List<RoleMenu>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query =session.createQuery("FROM RoleMenu WHERE roleId=?");
				query.setParameter(0, roleId);
				return query.list();
			}
		});
	}

	@Override
	public void deleteMenuByRoleId(final int roleId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery("delete from tb_role_menu where role_id=?");
				query.setParameter(0, roleId);
				query.executeUpdate();
				return null;
			}
		});
	}

}
