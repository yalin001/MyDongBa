package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysNotice;

public interface SysNoticeService {
	PageObject<SysNotice> findPageObjects(
			String username,
			Integer pageCurrent);
	int saveObject(SysNotice entity);
	int deleteObjects(Integer...ids) ;
}
