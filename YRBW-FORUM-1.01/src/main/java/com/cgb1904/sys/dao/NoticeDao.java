package com.cgb1904.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cgb1904.sys.po.Notice;

@Mapper
public interface NoticeDao extends BaseDao<Notice> {

	//查询最新的公告
	Notice findLatestNotice();
}
