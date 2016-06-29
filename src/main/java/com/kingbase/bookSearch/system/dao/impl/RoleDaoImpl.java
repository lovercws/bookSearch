package com.kingbase.bookSearch.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.kingbase.bookSearch.common.dao.impl.HibernateDaoImpl;
import com.kingbase.bookSearch.system.bean.Role;
import com.kingbase.bookSearch.system.dao.IRoleDao;

@Repository
public class RoleDaoImpl  extends HibernateDaoImpl<Role> implements IRoleDao{

}
