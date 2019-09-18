package com.cgb1904.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cgb1904.sys.po.Post;

/**
 * 发帖模块
 * @author Tarena
 *
 */
@Mapper
public interface PostDao extends BaseDao<Post>{

	Post findPost(Integer id);

	/**
	 * 基于条件分页查询日志信息
	 * @param username  查询条件(例如查询哪个用户的日志信息)
	 * @param startIndex 当前页的起始位置
	 * @param pageSize 当前页的页面大小
	 * @return 当前页的日志记录信息
	 * 数据库中每条日志信息封装到一个SysLog对象中
	 */
	List<Post> findPageObjects(
			@Param("title")String  title,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 基于条件查询总记录数
	 * @param username 查询条件(例如查询哪个用户的日志信息)
	 * @return 总记录数(基于这个结果可以计算总页数)
	 * 说明：假如如下方法没有使用注解修饰，在基于名字进行查询
	 * 时候会出现There is no getter for property named
	 * 'username' in 'class java.lang.String'
	 */
	int getRowCount(@Param("title") String title);
}
