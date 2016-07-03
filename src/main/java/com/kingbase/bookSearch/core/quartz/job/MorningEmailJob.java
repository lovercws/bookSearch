package com.kingbase.bookSearch.core.quartz.job;

import java.util.List;

import javax.annotation.Resource;

import com.kingbase.bookSearch.core.email.SimpleMailService;
import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.service.IUserService;

/**
 * 每天早上  对系统所有的用户发送邮件 说早安
 * @author ganliang
 */
public class MorningEmailJob {

	@Resource
	private SimpleMailService mailService;
	@Resource
	private IUserService userService;
	
	/**
	 * 早上好
	 */
	public void morning(){
		//获取系统所有的用户
		List<User> userList=userService.findAllUser();
		for (User user : userList) {
			String email=user.getEmail();
			 mailService.sendSimpleMail(email, "早上好!Good morning!", "早上是一天最美好的时刻,因为我们感觉我们拥有着一整天,所以可以安心的浪费,但是当我们夜晚睡觉的时候,会很心痛,又浪费了一天.");
		}
	}
}
