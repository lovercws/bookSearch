package com.kingbase.bookSearch.auth.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;

import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.service.IUserService;

/**
 * 记住密码 自动登录
 * @author ganliang
 *
 */
public class RememberAuthenticationFilter extends UserFilter {

	@Resource
	private IUserService userService;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		// 是登录页面 则直接返回true
		if (isLoginRequest(request, response)) {
			return true;
		} else {
			// 获取主题
			Subject subject = getSubject(request, response);
			// 如果用户【未认证】 但是用户使用【记住我】功能
			if (!subject.isAuthenticated() && subject.isRemembered()) {
				// 获取rememberMe的用户名称
				Object principal = subject.getPrincipal();
				if (null != principal) {
					//从session中 获取登录用户信息
					Session session = subject.getSession(true);
					User sessionUser = (User) session.getAttribute(User.SESSION_USER);
					
					//如果session不保存用户信息；则从数据库中获取
					if (sessionUser == null) {
						sessionUser = userService.getUserByName(String.valueOf(principal));
						subject.getSession().setAttribute(User.SESSION_USER, sessionUser);
					}
					return true;
				}
			}
		}
		return true;
	}
}
