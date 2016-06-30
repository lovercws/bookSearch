package com.kingbase.bookSearch.system.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.service.IUserService;
import com.kingbase.bookSearch.system.service.impl.UserServiceImpl;

@Scope("prototype")
@Controller("UserManagerAction")
public class UserManagerAction extends BaseAction<User>{

	private static final long serialVersionUID = -4818437369420152646L;
	private static final Logger log=Logger.getLogger(UserManagerAction.class);
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    
	private User user=this.getModel();
	
	@Resource
	private IUserService userService=new UserServiceImpl();
	/**
	 * 获取用户个人详细信息列表
	 * @return
	 */
	public String userDetailMessage(){
		User sessionUser = (User) request.getSession().getAttribute(User.SESSION_USER);
		String json = gson.toJson(sessionUser);
		request.setAttribute("userDetailJson", json.replace("\"", "'"));
		log.info("用户详情-->"+json);
		return "userDetailMessage";
	}
	
	/**
	 * 修改个人详细信息列表
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	public String changeDetailMessage() throws IOException{
		log.info("修改用户详情-->"+user);
		User sessionUser=userService.changeDetailMessage(user);
		request.getSession().setAttribute(User.SESSION_USER, sessionUser);
		
		String json = gson.toJson(sessionUser);
		request.setAttribute("userDetailJson", json.replace("\"", "'"));
		return "userDetailMessage";
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public String userList(){
		List<User> userList=userService.queryUser(user);
		log.info("查询用户列表-->"+userList);
		request.setAttribute("userList", userList);
		return "userList";
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	public String saveUser(){
		log.info("保存用户-->"+user);
		if(user.getId()==0){
			user.setPassword(User.INIT_PASSWORD);
		}
		userService.saveOrUpdate(user);
		return userList();
	}
	
	/**
	 * 初始化用户的密码
	 * @return
	 */
	public String initPassword(){
		log.info("初始化密码-->"+user);
		user.setPassword(User.INIT_PASSWORD);
		userService.updateUserPassword(user);
		return userList();
	}
	
	/**
	 * 导出用户列表
	 * @throws IOException 
	 */
	public void exportUser() throws IOException{
		
		List<User> users = userService.queryUser(user);
		
		response.reset();
		// 文件下载
		response.setContentType("application/vnd.ms-excel");
		String filename = "用户报表（"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ "）";
		
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		response.setHeader("Content-disposition", "attachment;filename="+ filename + ".xls");
		response.setBufferSize(1024);
		
		//获取excel表单
		Workbook workbook=userService.exportUser(users);
		//写入数据
		workbook.write(response.getOutputStream());
		workbook.close();
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String deleteUser(){
		log.info("删除用户-->"+user);
		userService.deleteUser(user);
		return userList();
	}
	
	/**
	 * 修改当前登录用户的密码
	 * @return
	 */
	public String changePassword(){
		return "changePassword";
	}
	
	/**
	 * 修改密码
	 * @throws IOException 
	 */
	@ResponseBody
	public void changePwd() throws IOException{
		log.info("修改密码-->"+user);
		User sessionUser = (User) request.getSession().getAttribute(User.SESSION_USER);
		boolean success=false;
		if(sessionUser.getPassword().equals(user.getPassword())){
			sessionUser.setPassword(user.getNewPassword());
			userService.updateUserPassword(sessionUser);
			success=true;
			
			//重设sessionUser
			request.getSession().setAttribute(User.SESSION_USER, sessionUser);
		}
		response.getWriter().print("{success:"+success+"}");
	}

	/**
	 * 用户统计
	 * @return
	 */
	public String userStatistics(){
		log.info("用户注册统计，访问你统计....................");

		//获取用户注册量统计数据
		String regStatistics=userService.getUserRegistoryStatistics();
		log.info("注册统计数据-->>"+regStatistics);
		request.setAttribute("regStatistics", regStatistics);
		
		//获取用户访问量统计数据
		String accessStatistics=userService.getUserAccessStatistics();
		log.info("访问量统计数据-->>"+accessStatistics);
		request.setAttribute("accessStatistics", accessStatistics);
		
		return "userStatistics";
	}
	
	/**
	 * 用户注册量统计
	 * @return
	 */
	public String userRegistoryStatistics(){
		return "userRegistoryStatistics";
	}
	
	/**
	 * 用户访问量统计
	 * @return
	 */
	public String userAccessStatistics(){
		return "userAccessStatistics";
	}
	
	/**
	 * 在线用户列表
	 * @return
	 */
	public String onlineUser(){
		List<User> userList = userService.getOnlineUser(user);
		log.info("获取在线用户-->"+userList);
		request.setAttribute("userList", userList);
		return "onlineUser";
	}
	
	/**
	 * 进入到首页
	 * @return
	 */
	public String index(){
		return "index";
	}
}
