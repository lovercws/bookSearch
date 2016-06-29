package com.kingbase.bookSearch.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingbase.bookSearch.system.bean.Role;

/**
 * 角色接口
 * @author ganliang
 */
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public interface IRoleService {

	/**
	 * 获取所有的角色
	 * @return
	 */
	public List<Role> getAllRole();

	/**
	 * 保存角色
	 * @param role 角色实体对象
	 */
	@Transactional(readOnly=false)
	public void saveRole(Role role);

	/**
	 * 删除角色
	 * @param role
	 */
	@Transactional(readOnly=false)
	public void deleteRole(Role role);


}
