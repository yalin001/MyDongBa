package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysUser;

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
