package com.iotlab.integrityarchives.realm;

import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm
 * @author JH
 *
 */
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	/**
	 * 为当限前登录的用户授予角色和权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>();
		roles.add("user");
		authorizationInfo.setRoles(roles);
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String number = (String) token.getPrincipal();
		User user = userService.findByNumber(number);
		if(user != null){
			SecurityUtils.getSubject().getSession().setAttribute("user", user); // 当前用户信息存到session中
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserNumber(), user.getUserPasswd(), "user");
			return authcInfo;
		}else{
			return null;				
		}
	}

}
