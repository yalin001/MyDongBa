package com.cgb1904.sys.service;
import java.util.Map;

import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.SysManager;

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
