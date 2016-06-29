package com.kingbase.bookSearch.system.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingbase.bookSearch.system.bean.User;

/**
 * 角色接口
 * @author ganliang
 */
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public interface IUserService {

	/**
	 * 查询用户
	 * @param user 用户实体
	 * @return
	 */
	public List<User> queryUser(User user);

	/**
	 * 保存或者更新用户
	 * @param user 用户实体
	 */
	@Transactional(readOnly=false)
	public void saveOrUpdate(User user);

	/**
	 * 删除用户
	 * @param user 用户实体
	 */
	@Transactional(readOnly=false)
	public void deleteUser(User user);

	/**
	 * 初始化用户密码
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void updateUserPassword(User user);

	/**
	 * 导出用户
	 * @param users
	 * @return
	 * @throws IOException 
	 */
	public Workbook exportUser(List<User> users) throws IOException;

	/**
	 * 获取在线用户
	 * @param user
	 * @return
	 */
	public List<User> getOnlineUser(User user);

	/**
	 * 根据用户名来获取用户名
	 * @param userName
	 * @return
	 */
	public User getUserByName(String userName);
	/**
	 * 修改用户信息
	 * @param user
	 * @return 
	 */
	public User changeDetailMessage(User user);

	/**
	 * 根据邮箱查找用户
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email);

	/**
	 * 激活用户
	 * @param user
	 */
	public void avtiveUser(User user);

}
