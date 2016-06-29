/**
 * @author ganliang
 *
 */
package com.kingbase.bookSearch.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.kingbase.bookSearch.system.bean.User;

public class PermissionFilter implements Filter {

	private static final Logger log = Logger.getLogger(PermissionFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain china)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String servletPath = request.getServletPath();
		String contextPath = request.getContextPath();
		
		//过滤url
		if (servletPath.indexOf("/portal/")>-1) {
			china.doFilter(request, response);
		} else {
			User user = (User) request.getSession().getAttribute(User.SESSION_USER);
			// 用户为空
			if (user == null) {
				response.sendRedirect(contextPath+"/portal/loginAction_goLogin.do");
			} else {
				if("admin".equals(user.getName())||"cws".equals(user.getName())){
					china.doFilter(request, response);
				}else{
					Subject subject = SecurityUtils.getSubject();
					boolean permitted = subject.isPermitted(servletPath.substring(1));
					if(permitted){
						china.doFilter(request, response);
					}else{
						log.info("授权失败");
						response.sendRedirect(contextPath+"/unauth.jsp");
					}
				}
			}
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("PermissionFilter run.........");
	}

}