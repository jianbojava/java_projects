package com.cocosh.framework.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cocosh.sys.model.User;
import com.cocosh.sys.service.RolePermService;
import com.cocosh.sys.service.UserService;

/**
 * 自定义relam
 * 
 * @author jerry
 */
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private RolePermService rPermService;

	/**
	 * 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// String username = (String) principals.getPrimaryPrincipal();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		if (user != null) {
			// 获取当前用户的资源集合
			SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
			List<String> perms = rPermService.queryAvailable(user.getId());
			if (!perms.isEmpty()) {
				auth.addStringPermissions(perms);
			}
			auth.addStringPermission("*:*");
			return auth;
		}
		return null;
	}

	/**
	 * 获取身份验证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = (String) token.getUsername();
		String password = String.valueOf(token.getPassword());
		User user = userService.queryLogin(username, password);
		if (user != null) {
			Subject subject = SecurityUtils.getSubject();
			subject.getSession().setAttribute("user", user);
			if (user.getIs_locked() == 1) {
				throw new LockedAccountException();// 账号被锁定
			}
			if((user.getEnabled()==null)||(user.getEnabled()!=1)){
				throw new DisabledAccountException();// 账户未审核
			}
			if(user.getUser_type()==null||(user.getUser_type()!=0&&user.getUser_type()!=1)){
				throw new IncorrectCredentialsException();//只有员工和合伙人可以登录
			}
			return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
		}
		return null;
	}

}
