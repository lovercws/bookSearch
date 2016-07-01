package com.kingbase.bookSearch.portal.action;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.common.utils.HostUtil;
import com.kingbase.bookSearch.common.utils.MD5keyBean;
import com.kingbase.bookSearch.core.email.SimpleMailService;
import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.service.IUserService;

@Scope("prototype")
@Controller("RegistryAction")
public class RegistryAction extends BaseAction<User> {

	private static final long serialVersionUID = 3496813277489417488L;
    private static final Logger log=Logger.getLogger(RegistryAction.class);
    
	private User user=this.getModel();
	
	@Resource
	private IUserService userService;
	@Resource
	private SimpleMailService mailService;
	// 跳转到注册页面
	public String goReg() {
		return "goReg";
	}

	// 跳转到忘记密码页面
	public String forgetPassword() {
		return "forgetPassword";
	}

	/**
	 * 发送邮件
	 * @return
	 * @throws IOException
	 */
	public String verifyEmail() throws IOException {
		String msg="";
		
		String verifyCode=user.getVerifyCode();
		String sessionVerifyCode=(String) request.getSession().getAttribute(User.MAIL_VERIFYCODE);
		if(verifyCode!=null&&verifyCode.equals(sessionVerifyCode)){
			User findUser=userService.findUserByEmail(user.getEmail());
			if(findUser!=null){
				
				//发送邮件
				String host = HostUtil.getHost(request, "portal/registryAction_resetPassword.do?id="+user.getId());
				mailService.sendSimpleMail(user.getEmail(), "验证邮箱", "<a href='"+host+"'>点击验证邮箱</a>");
			    msg="邮件发送成功";
			}else{
				msg="该邮箱未注册";
			}
		}else{
			msg="验证码不正确";
		}
		
		request.setAttribute("forwardMsg", msg);
		return "forward";
	}
	
	/**
	 * 发送邮件
	 * @return
	 * @throws IOException
	 */
	public String resetPassword() throws IOException {
		return "resetPassword";
	}
	
	/**
	 * 发送邮件
	 * @return
	 * @throws IOException
	 */
	public String changePwd() throws IOException {
		user.setPassword(MD5keyBean.Md5(user.getNewPassword()));
		log.info("修改用户密码-->>"+user);
		
		userService.updateUserPassword(user);
		request.setAttribute("forwardMsg", "密码修改成功!");
		return "forward";
	}
	
	/**
	 * 注册
	 * @return 跳转到注册跳转页面
	 * @throws UnknownHostException 
	 */
	public String register() throws UnknownHostException {
		log.info("注册用户-->>"+user);
		
		String returnURL="";
		String msg="";
		
		//检测验证码
		String verifyCode=user.getVerifyCode();
		String sessionVerifyCode=(String) request.getSession().getAttribute(User.REGISTRY_VERIFYCODE);
		if(verifyCode!=null&&verifyCode.equalsIgnoreCase(sessionVerifyCode)){
			//检测email唯一性
			User existsUser = userService.findUserByEmail(user.getEmail());
			//该邮件已经被使用
			if(existsUser!=null){
				msg="该邮箱已经注册用户，请重新选择邮箱";
				returnURL="goReg";
			}else{
				//添加注册用户信息
				user.setRegistryDate(new Date());
				user.setActive(false);
				user.setId(0);
				user.setPassword(MD5keyBean.Md5(user.getPassword()));
				
				//保存用户
				userService.saveOrUpdate(user);
				
				//发送激活账号email
				String host = HostUtil.getHost(request, "portal/registryAction_activeUser.do?email="+user.getEmail());
				mailService.sendSimpleMail(user.getEmail(), "验证注册信息", "<a href='"+host+"'>点击激活邮箱</a>");
				
				msg="邮件发送成功,请激活用户";
				returnURL="forward";
			}
		}else{
			msg="验证码不匹配";
			returnURL="goReg";
		}
		
		request.setAttribute("forwardMsg", msg);
		request.setAttribute("user", user);
		return returnURL;
	}
	
	/**
	 * 激活用户
	 * @return
	 */
	public String activeUser(){
		log.info("用户激活-->>"+user);
		user.setActive(true);
		userService.avtiveUser(user);
		
		request.setAttribute("forwardMsg", "用户激活成功!");
		return "forward";
	}
}
