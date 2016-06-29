package com.kingbase.bookSearch.system.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.kingbase.bookSearch.core.excel.ExcelUtil;
import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.dao.IUserDao;
import com.kingbase.bookSearch.system.dao.impl.UserDaoImpl;
import com.kingbase.bookSearch.system.service.IUserService;

/**
 * 角色实现类
 * @author ganliang
 */
@Service
public class UserServiceImpl implements IUserService{

	@Resource
	private IUserDao userDao=new UserDaoImpl();
	
	@Override
	public List<User> queryUser(User user) {
		String userName = user.getName();
		int id = user.getId();
		List<User> userList=null;
		//按照用户名 查询
		if(id==0&&userName!=null&&!"".equals(userName)){
			userList=userDao.find("FROM User WHERE name like '%"+userName+"%'");
		}
		//查询所有的用户
		else{
			userList=userDao.findAll();
		}
		if(userList!=null&&userList.size()>0){
			for (User usr : userList) {
				usr.setRoles(null);
			}
		}
		return userList;
	}

	@Override
	public void saveOrUpdate(User user) {
		//保存
		if(user.getId()==0){
			userDao.save(user);
		}
		//更新
		else{
			userDao.updateUser(user);
		}
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public void updateUserPassword(User user) {
		userDao.updateUserPassword(user.getId(),user.getPassword());
	}

	@Override
	public Workbook exportUser(List<User> users) throws IOException {
		if(users==null||users.size()==0){
			return null;
		}
		//组装excel头
		List<String> headers=new ArrayList<String>();
		headers.add("id");headers.add("name");headers.add("password");
		headers.add("nickname");headers.add("sex");headers.add("email");
		headers.add("mobile");headers.add("contactTel");headers.add("address");
		headers.add("remark");headers.add("headerImage");headers.add("online");
		headers.add("birthday");headers.add("registryDate");headers.add("lastAccessDate");
		headers.add("active");
		//组装excel体
		List<List<Object>> data=new ArrayList<List<Object>>();
		for (User user : users) {
			List<Object> body=new ArrayList<Object>();
			body.add(user.getId());body.add(user.getName());body.add(user.getPassword());
			body.add(user.getNickname());body.add(user.getSex());body.add(user.getEmail());
			body.add(user.getMobile());body.add(user.getContactTel());body.add(user.getAddress());
			body.add(user.getRemark());body.add(user.getHeaderImage());body.add(user.isOnline());
			body.add(user.getBirthday());body.add(user.getRegistryDate());body.add(user.getLastAccessDate());
			body.add(user.isActive());
			data.add(body);
		}
		//生成excel表单
		return ExcelUtil.createWorkbook("菜单", headers, data, ExcelUtil.EXCEL_XLS);
	}

	@Override
	public List<User> getOnlineUser(User user) {
		List<User> userList = userDao.find("FROM User where online=true");
		for (User usr : userList) {
			usr.setRoles(null);
		}
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByName(String userName) {
		List<User> userList = (List<User>) userDao.find("FROM User WHERE name=?",new Object[]{userName});
		for (User user : userList) {
			user.setRoles(null);
		}
		return  userList!=null&&userList.size()>0?userList.get(0):null;
	}

	@Override
	public User changeDetailMessage(User user) {
		//先获取user
		User usr = userDao.get(user.getId());
		
		//更改密码 
		usr.setName(user.getName());
		usr.setNickname(user.getNickname());
		usr.setSex(user.getSex());
		usr.setMobile(user.getMobile());
		usr.setContactTel(user.getContactTel());
		usr.setBirthday(user.getBirthday());
		usr.setHeaderImage(user.getHeaderImage());
		usr.setEmail(user.getEmail());
		usr.setRemark(user.getRemark());
		
		userDao.update(usr);	
		usr.setRoles(null);
		
		return usr;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userDao.findUniqueEntity("FROM User where email=?", email);
		if(user!=null){
			user.setRoles(null);
		}
		return user;
	}
}
