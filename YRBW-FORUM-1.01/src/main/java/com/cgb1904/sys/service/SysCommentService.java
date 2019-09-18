package com.cgb1904.sys.service;

import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.SysComment;

public interface SysCommentService {
	PageObject<SysComment> findPageObjects(
			 String content,
			 Integer pageCurrent);

	int deleteObjects(Integer...ids);
}
