package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysComment;

public interface SysCommentService {
	
	PageObject<SysComment> findPageObjects(
			 String content,
			 Integer pageCurrent);
	int deleteObjects(Integer...ids);
}
