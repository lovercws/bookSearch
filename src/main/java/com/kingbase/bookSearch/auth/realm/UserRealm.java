package com.kingbase.bookSearch.auth.realm;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.struts2.ServletActionContext;

import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.service.IUserRoleService;
import com.kingbase.bookSearch.system.service.IUserService;
import com.kingbase.bookSearch.system.service.impl.UserRoleServiceImpl;
import com.kingbase.bookSearch.system.service.impl.UserServiceImpl;

/**
 * 用户授权
 * @author ganliang
 */
public class UserRealm extends AuthorizingRealm {

	private static final Logger log = Logger.getLogger(UserRealm.class);

	@Resource
	private IUserService userService = new UserServiceImpl();

	@Resource
	private IUserRoleService userRoleService = new UserRoleServiceImpl();

	/**
	 * 权限授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		log.info("授权认证：" + principal.getRealmNames());
		String userName = (String) principal.fromRealm(getName()).iterator().next();
		if (userName != null) {
			User user = userService.getUserByName(userName);
			if (user != null) {
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				List<String> permissions = userRoleService.getRolePermissions(user.getId());
				for (String permission : permissions) {
					if (permission != null && !"".equals(permission)) {
						if (!permission.startsWith("/")) {
							permission = "/" + permission;
						}
						info.addStringPermission(permission);
					}
				}
				return info;
			}
		}
		return null;
	}

	/**
	 * 登录认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		log.info("authc name:" + token.getUsername());
		// 获取用户名称
		String userName = token.getUsername();
		char[] password = token.getPassword();
		if (userName != null && !"".equals(userName)) {
			User user = userService.getUserByName(userName);
			if (user != null && user.getPassword().equals(new String(password))) {

				// 将当前用户信息缓存到session中
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute(User.SESSION_USER, user);

				return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
			}
		}
		return null;
	}

}
