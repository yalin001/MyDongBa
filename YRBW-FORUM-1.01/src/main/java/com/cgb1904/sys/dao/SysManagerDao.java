package com.cgb1904.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cgb1904.sys.po.SysManager;

@Mapper
public interface SysManagerDao {

List<SysManager> findPageObjects(
		@Param("username") String username,
		@Param("startIndex")Integer startIndex,
		@Param("pageSize")Integer pageSize);

int getRowCount(@Param("username") String username);

int insertObject(SysManager entity);

SysManager findObjectById(Integer id);

int updateObject(SysManager entity);

	/* int deleteObjectsByUserId(Integer managerId); */


SysManager findManagerByUserName(String username);

/**
 * 基于用户id修改用户的密码
 * @param password 新的密码
 * @param salt 密码加密时使用的盐值
 * @param id 用户id
 * @return
 */
int updatePassword(
		@Param("password")String password,
		@Param("salt")String salt,
		@Param("id")Integer id);
}
