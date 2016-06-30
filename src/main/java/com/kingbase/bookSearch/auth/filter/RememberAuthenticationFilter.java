package com.kingbase.bookSearch.auth.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;

/**
 * 记住密码 自动登录
 * @author ganliang
 *
 */
public class RememberAuthenticationFilter extends UserFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		if (!subject.isAuthenticated() && subject.isRemembered()) {
			Object principal = subject.getPrincipal();
			if (null != principal) {
				System.out.println(principal);
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}
}
