package com.kingbase.bookSearch.system.dao;

import com.kingbase.bookSearch.common.dao.IHibernateDao;
import com.kingbase.bookSearch.system.bean.User;

public interface IUserDao extends IHibernateDao<User>{

	public void updateUserPassword(int id, String password);

	public void updateUser(User user);

	public void updateUserActive(String email, boolean active);

}
