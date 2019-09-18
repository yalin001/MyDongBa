package com.cgb1904.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cgb1904.sys.po.SysNotice;

@Mapper
public interface SysNoticeDao {
	List<SysNotice> findPageObjects(
			@Param("username") String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("username") String username);

	int insertObject(SysNotice entity);
	
	int deleteObjects(@Param("ids")Integer...ids);
	
	
}
