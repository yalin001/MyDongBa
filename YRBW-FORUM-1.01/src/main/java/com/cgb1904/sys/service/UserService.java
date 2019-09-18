package com.cgb1904.sys.service;

import org.springframework.stereotype.Service;

import com.cgb1904.sys.po.SysUser;

@Service
public interface UserService {

	/**
	 * 输入原密码、新密码、确认密码进行修改密码
	 * @param password 原密码
	 * @param newPassword 新密码
	 * @param cfgPassword 确认密码
	 * @return 
	 */
	int updatePassword(
			String password,String newPassword,String cfgPassword);
	
	 /**
	  * 保存用户以及用户对应的角色信息
	  * @param entity
	  * @param roleIds
	  * @return
	  */
	 int saveObject(SysUser entity);
}
