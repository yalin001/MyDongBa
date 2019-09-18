package com.cgb1904.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cgb1904.sys.po.SysUser;

@Mapper
public interface SysUserDao {
	List<SysUser> findPageObjects(
			@Param("username") String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("username") String username);

	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid);

	int insertObject(SysUser entity);
	
	SysUser findObjectById(Integer id);
	
	int updateObject(SysUser entity);
}
