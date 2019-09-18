package com.cgb1904.sys.serviceImpl;

import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cgb1904.common.exception.ServiceException;
import com.cgb1904.sys.dao.UserDao;
import com.cgb1904.sys.po.SysUser;
import com.cgb1904.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao sysUserDao;
	
	@Override
	public int updatePassword(String password, String newPassword, String cfgPassword) {
		//1.判定新密码与密码确认是否相同
		if(StringUtils.isEmpty(newPassword))
			throw new IllegalArgumentException("新密码不能为空");
		if(StringUtils.isEmpty(cfgPassword))
			throw new IllegalArgumentException("确认密码不能为空");
		if(!newPassword.equals(cfgPassword))
			throw new IllegalArgumentException("两次输入的密码不相等");
		//2.判定原密码是否正确
		if(StringUtils.isEmpty(password))
			throw new IllegalArgumentException("原密码不能为空");
		//获取登陆用户
		SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
		SimpleHash sh=new SimpleHash("MD5",
				password, user.getSalt(), 1);
		if(!user.getPassword().equals(sh.toHex()))
			throw new IllegalArgumentException("原密码不正确");
		//3.对新密码进行加密
		String salt=UUID.randomUUID().toString();
		sh=new SimpleHash("MD5",newPassword,salt, 1);
		//4.将新密码加密以后的结果更新到数据库
		int rows=sysUserDao.updatePassword(sh.toHex(), salt,user.getId());
		if(rows==0)
			throw new ServiceException("修改失败");
		//long t2=System.currentTimeMillis();
		//System.out.println("t2-t1="+(t2-t1));
		return rows;
	}
	
	@Transactional
	@Override
	public int saveObject(SysUser entity) {
		//1.参数校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		//2.对密码进行加密
		String salt=UUID.randomUUID().toString();
		SimpleHash sh=new SimpleHash(
				"MD5",//algorithmName加密算法
				entity.getPassword(),//source 被加密的对象
				salt, //salt 盐值
				1);//hashIterations 加密
		entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		//3.保存用户自身信息
		int rows=sysUserDao.insertObject(entity);
		//5.返回结果
		return rows;
	}

}
