package com.cgb1904.sys.dao;

import java.util.List;


/*
 * 封装帖子，评论的共性方法
 */
public interface BaseDao<T> {

	//保存单个对象
	int saveObject(T object);
	
	//基于id查询对象
	List<T> findObjects(Integer id);
	
	//删除单个对象
	int deleteObject(Integer id);
	
	//修改
	int updateObject(T object);
}
