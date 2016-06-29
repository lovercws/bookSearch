package com.kingbase.bookSearch.system.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.kingbase.bookSearch.common.dao.impl.HibernateDaoImpl;
import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.dao.IUserDao;

@Repository
public class UserDaoImpl  extends HibernateDaoImpl<User> implements IUserDao{

	@Override
	public void updateUserPassword(final int id, final String password) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("UPDATE tb_user set user_password=? where user_id=?");
				sqlQuery.setParameter(0, password);
				sqlQuery.setParameter(1, id);
				sqlQuery.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void updateUser(final User user) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("update tb_user set user_name=?,user_nickname=?,user_sex=?,user_mobile=?,user_contactTel=?,user_header_image=?,user_email=?,user_birthday=?,user_address=?,user_remark=? where user_id=?");
				sqlQuery.setParameter(0, user.getName());
				sqlQuery.setParameter(1, user.getNickname());
				sqlQuery.setParameter(2, user.getSex());
				sqlQuery.setParameter(3, user.getMobile());
				sqlQuery.setParameter(4, user.getContactTel());
				sqlQuery.setParameter(5, user.getHeaderImage());
				sqlQuery.setParameter(6, user.getEmail());
				sqlQuery.setParameter(7, user.getBirthday());
				sqlQuery.setParameter(8, user.getAddress());
				sqlQuery.setParameter(9, user.getRemark());
				sqlQuery.setParameter(10, user.getId());
				sqlQuery.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void updateUserActive(final String email, final boolean active) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("UPDATE tb_user set user_active=? where user_email=?");
				sqlQuery.setParameter(0, active);
				sqlQuery.setParameter(1, email);
				sqlQuery.executeUpdate();
				return null;
			}
		});
	}

}
