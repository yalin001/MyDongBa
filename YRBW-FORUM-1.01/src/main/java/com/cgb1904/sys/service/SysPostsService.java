package com.cgb1904.sys.service;

import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.SysPosts;

public interface SysPostsService {
    /**
 * 通过此方法实现分页查询操作
 * @param name 基于条件查询时的参数名
 * @param pageCurrent 当前的页码值
 * @return 当前页记录+分页信息
 */
	PageObject<SysPosts> findPageObjects(
		 String  name,
		 Integer pageCurrent);
	//删除数据
	int deleteObjects(Integer[] ids);
}
