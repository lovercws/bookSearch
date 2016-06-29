package com.kingbase.bookSearch.system.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.kingbase.bookSearch.common.dao.impl.HibernateDaoImpl;
import com.kingbase.bookSearch.system.bean.Role;
import com.kingbase.bookSearch.system.bean.UserRole;
import com.kingbase.bookSearch.system.dao.IUserRoleDao;
@Repository
public class UserRoleDaoImpl  extends HibernateDaoImpl<UserRole> implements IUserRoleDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getSelectedRole(final int userId) {
		List<Role> roleList = (List<Role>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("SELECT role.* from tb_user_role ur INNER JOIN tb_role role on(role.role_id=ur.role_id) where ur.user_id=?");
				sqlQuery.addEntity(Role.class);
				sqlQuery.setParameter(0, userId);
				List<Role> list = sqlQuery.list();
				return list;
			}
		});
		return roleList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getUnSelectedRole(final int userId) {
		List<Role> roleList = (List<Role>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("SELECT role.* from tb_role role where role.role_id not in(select ur.role_id from tb_user_role ur where ur.user_id=?)");
				sqlQuery.addEntity(Role.class);
				sqlQuery.setParameter(0, userId);
				List<Role> list = sqlQuery.list();
				return list;
			}
		});
		return roleList;
	}

	@Override
	public void deleteUserRoles(final int userId, final String roleIds) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				if(roleIds!=null){
					StringBuilder builder=new StringBuilder();
					String[] strings = roleIds.split(",");
					for (String string : strings) {
						builder.append("'"+string+"',");
					}
					int lastIndexOf = builder.lastIndexOf(",");
					if(lastIndexOf!=-1){
						builder.deleteCharAt(lastIndexOf);
					}
					SQLQuery sqlQuery = session.createSQLQuery("delete from tb_user_role where user_id=? and role_id in("+builder.toString()+")");
					sqlQuery.setParameter(0, userId);
					sqlQuery.executeUpdate();
				}
				return null;
			}
		});
	}

	public List<Role> getRolePermissions2(final int userId) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@SuppressWarnings("unchecked")
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("select r.role_name name,(select group_concat(m.menu_src) from tb_role_menu rm left join tb_menu m on(m.menu_id=rm.menu_id) where rm.role_id=r.role_id) permissions from tb_user_role ur left join tb_role r on(ur.role_id=r.role_id) where ur.user_id=?");
				sqlQuery.setParameter(0, userId);
				List<Role> roles=new ArrayList<Role>();
				
				List<Object[]> list = sqlQuery.list();
				for (Object[] objects : list) {
					Role role=new Role();
					
					//角色名称
					String roleName=String.valueOf(objects[0]);
					role.setName(roleName);
					
					//角色下的权限列表
					Object object = objects[1];
					if(object!=null){
						String permissions=String.valueOf(object);
						String[] strings = permissions.split(",");
						for (String string : strings) {
							if(!"".equals(string)){
								role.getPermissions().add(string);
							}
						}
					}
				}
				return roles;
			}
		});
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRolePermissions(final int userId) {
		List<String> permissions = (List<String>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery("select distinct m.menu_src from tb_role_menu rm left join tb_menu m on(m.menu_id=rm.menu_id) where rm.role_id in(select ur.role_id from tb_user_role ur where ur.user_id=?)");
				sqlQuery.setParameter(0, userId);
				List<String> permissions = sqlQuery.list();
				return permissions;
			}
		});
		return permissions;
	}

}
