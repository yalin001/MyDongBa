package com.cgb1904.sys.service;

import java.util.List;

import com.cgb1904.sys.po.Comment;

public interface BaseService <T>{

	//保存单个对象
	int saveObject(T object);
	//查询对象
	List<T> findObjects(Integer id);
	
	//删除单个对象
	int deleteObject(Integer id);
	
	//修改
	int updateObject(T object);
}
