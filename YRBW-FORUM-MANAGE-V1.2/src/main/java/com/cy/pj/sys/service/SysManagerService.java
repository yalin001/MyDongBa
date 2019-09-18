package com.cy.pj.sys.service;
import java.util.Map;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysManager;

public interface SysManagerService {
PageObject<SysManager> findPageObjects(
		String username,
		Integer pageCurrent);
int saveObject(SysManager entity);
Map<String, Object> findObjectById(
		Integer managerId);
int updateObject(SysManager entity);
int updatePassword(String password,
        String newPassword,
        String cfgPassword);

}
