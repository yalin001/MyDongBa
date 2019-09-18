package com.cy.pj.sys.service.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.sys.dao.SysManagerDao;
import com.cy.pj.sys.entity.SysManager;

@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private SysManagerDao sysManagerDao;
	@Override
	public void setCredentialsMatcher(
	    CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher=
		new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.获取用户名(用户页面输入)
				UsernamePasswordToken upToken=
				(UsernamePasswordToken)token;
				String username=upToken.getUsername();
				//2.基于用户名查询用户信息
				SysManager manager=
				sysManagerDao.findManagerByUserName(username);
				System.out.println(manager);
				//3.判定用户是否存在
				if(manager==null)
				throw new UnknownAccountException();
//				//4.判定用户是否已被禁用。
//				if(manager.getValid()==0)
//				throw new LockedAccountException();
				
				//5.封装用户信息
				ByteSource credentialsSalt=
				ByteSource.Util.bytes(manager.getSalt());
				//记住：构建什么对象要看方法的返回值
				SimpleAuthenticationInfo info=
				new SimpleAuthenticationInfo(
						manager,//principal (身份)
						manager.getPassword(),//hashedCredentials
						credentialsSalt, //credentialsSalt
						getName());//realName
				//6.返回封装结果
				return info;//返回值会传递给认证管理器(后续
				//认证管理器会通过此信息完成认证操作)

	}

}
