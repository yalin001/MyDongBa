package com.cgb1904.sys.service;

import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.SysNotice;

public interface SysNoticeService {
	PageObject<SysNotice> findPageObjects(
			String username,
			Integer pageCurrent);
	int saveObject(SysNotice entity);
	
	int deleteObjects(Integer...ids) ;
}
