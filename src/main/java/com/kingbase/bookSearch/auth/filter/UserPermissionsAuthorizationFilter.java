package com.kingbase.bookSearch.auth.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

/**
 * 用户自定义的权限
 * @author ganliang
 */
public class UserPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

	private static final Logger log = Logger.getLogger(UserPermissionsAuthorizationFilter.class);

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		String[] permissions = getPermissions(request);
		log.info("请求权限........................" + permissions[0]);

		return super.isAccessAllowed(request, response, permissions);
	}

	/**
	 * 获取当前用户登录的权限
	 * @param req
	 * @return
	 */
	public String[] getPermissions(ServletRequest req) {
		String[] permissions = new String[1];
		HttpServletRequest request = (HttpServletRequest) req;
		String path = request.getServletPath();
		permissions[0] = path;

		return permissions;
	}
}
