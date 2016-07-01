package com.kingbase.bookSearch.auth.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.kingbase.bookSearch.system.bean.User;
import com.kingbase.bookSearch.system.service.IUserRoleService;
import com.kingbase.bookSearch.system.service.IUserService;
import com.kingbase.bookSearch.system.service.impl.UserRoleServiceImpl;
import com.kingbase.bookSearch.system.service.impl.UserServiceImpl;

/**
 * 用户授权
 * 
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

				// 获取用户权限集合
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
		log.info("auth登录-->>" + token.toString());
		// 获取用户名称
		String userName = token.getUsername();
		char[] password = token.getPassword();
		if (userName != null && !"".equals(userName)) {
			// 获取用户
			User user = userService.getUserByName(userName);
			// 验证密码是否正确
			if (user != null && user.getPassword().equals(new String(password))) {
				// 将用户信息保存到session中
				Subject subject = SecurityUtils.getSubject();
				subject.getSession(true).setAttribute(User.SESSION_USER, user);

				return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
			}
		}
		return null;
	}

}
