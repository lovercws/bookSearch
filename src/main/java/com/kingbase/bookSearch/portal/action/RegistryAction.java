package com.kingbase.bookSearch.portal.action;

import java.io.IOException;
import java.net.InetAddress;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kingbase.bookSearch.common.action.BaseAction;
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
				String hostAddress = InetAddress.getLocalHost().getHostAddress();
				mailService.sendSimpleMail(user.getEmail(), "验证邮箱", "<a href='http://"+hostAddress+":8081/bookSearch/portal/RegistryAction_resetPassword.do?id="+findUser.getId()+"'>点击验证邮箱</a>");
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
		user.setPassword(user.getNewPassword());
		log.info("修改用户密码-->>"+user);
		
		userService.updateUserPassword(user);
		request.setAttribute("forwardMsg", "密码修改成功!");
		return "forward";
	}
	
	/**
	 * 注册
	 * @return 跳转到注册跳转页面
	 */
	public String register() {
		request.setAttribute("forwardMsg", "邮件发送成功,请激活邮件注册用户");
		return "register";
	}
}
