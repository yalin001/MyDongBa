package com.cgb1904.sys.service;

import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.SysUser;

public interface SysUserService {
	PageObject<SysUser> findPageObjects(
			String username,
			Integer pageCurrent);

	int validById(Integer id,
			Integer valid);

	int saveObject(SysUser entity);
	
	
	SysUser findObjectById(Integer userId);
	int updateObject(SysUser entity); 
}
