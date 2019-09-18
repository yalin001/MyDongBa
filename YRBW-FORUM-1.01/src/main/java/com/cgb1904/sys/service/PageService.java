package com.cgb1904.sys.service;

import com.cgb1904.common.vo.PageObject;

public interface PageService<T> {
	 PageObject<T> findPageObjects(
			 String username,Integer pageCurrent);
}
