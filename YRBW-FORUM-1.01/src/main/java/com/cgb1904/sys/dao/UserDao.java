package com.cgb1904.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cgb1904.sys.po.SysUser;

@Mapper
public interface UserDao {

	/**
	 * 根据用户id修改密码
	 * @param password
	 * @param salt
	 * @param id
	 * @return
	 */
	int updatePassword(@Param("password") String password,
					@Param("salt")String salt,
					@Param("id")Integer id);
	
	
	/**
	 *  增加数据到数据库
	 * @param user
	 * @return
	 */
	int insertObject(SysUser user);


	/**
	 * 根据用户名查数据库
	 * @param username
	 * @return
	 */
	SysUser findUserByUserName(String username);
	
}
