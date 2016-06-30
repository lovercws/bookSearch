package com.kingbase.bookSearch.portal.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.core.email.SimpleMailService;
import com.kingbase.bookSearch.system.bean.User;

@Scope("prototype")
@Controller("LoginAction")
public class LoginAction extends BaseAction<User> {

	private static final long serialVersionUID = 3496813277489417488L;
	private static final Logger log=Logger.getLogger(LoginAction.class);

	private User user=this.getModel();
	
	@Resource
	private SimpleMailService mailService;
	/**
	 * 进入到登录页面
	 * @return 跳转到登陆页面
	 */
	public String goLogin() {
		return "goLogin";
	}

	/**
	 * 注销
	 * @return 跳转到首页
	 */
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "logout";
	}

	/**
	 * 用户登录
	 * @return 跳转到后台页面的首页
	 */
	public String login() {
		log.info("登录-->"+user);
		String returnURL="goLogin";//登录失败跳转页面
		
		String verifyCode = user.getVerifyCode();//用户输入的验证码
		
		String sessionVerifyCode = (String) request.getSession().getAttribute(User.LOGIN_VERIFYCODE);
		try {
			if(verifyCode==null||!verifyCode.equals(sessionVerifyCode)){
				request.setAttribute("errorMsg", "验证码输入错误!");
			}else{
				//密码加密
				/*String password = user.getPassword();
				MD5keyBean m = new MD5keyBean();
				password = m.getkeyBeanofStr(password);*/
				
				UsernamePasswordToken token=new UsernamePasswordToken(user.getName(), user.getPassword(),user.isRememberMe());
				
				Subject subject = SecurityUtils.getSubject();
				subject.login(token);
				
				//登录验证通过
				if(subject.isAuthenticated()){
					log.info("用户登录成功！");
					returnURL="login";
				}else{
					log.info("用户登录失败！");
					request.setAttribute("errorMsg", "登录失败！");
					token.clear();
				}
			}
		} catch (AuthenticationException e) {
			log.error(e);
			request.setAttribute("errorMsg", "登录失败！");
		}
		return returnURL;
	}

}
