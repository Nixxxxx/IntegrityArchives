package com.iotlab.integrityarchives.realm;

import java.util.HashSet;
import java.util.Set;

import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.service.AdminService;
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

/**
 * 自定义Realm
 * @author JH
 *
 */
public class AdminRealm extends AuthorizingRealm{

	@Autowired
	private AdminService adminService;
	
	/**
	 * 为当限前登录的用户授予角色和权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>();
		roles.add("admin");
		System.out.println("============================");
		authorizationInfo.setRoles(roles);
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String number = (String) token.getPrincipal();
		Admin admin = adminService.findByNumber(number);
		System.out.println(admin == null);
		if(admin != null){
			SecurityUtils.getSubject().getSession().setAttribute("admin", admin); // 当前用户信息存到session中
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(admin.getAdminNumber(), admin.getAdminPasswd(), "xx");
			return authcInfo;
		}else{
			return null;				
		}
	}

}
