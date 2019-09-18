package com.cgb1904.sys.serviceImpl;

import java.util.List;

import com.cgb1904.sys.dao.BaseDao;
import com.cgb1904.sys.service.BaseService;
import com.cgb1904.common.exception.ServiceException;

public class BaseServiceImpl<T> implements BaseService<T>{
	private BaseDao<T> baseDao;
	//构造注入
	public BaseServiceImpl(BaseDao<T> baseDao) {
		this.baseDao=baseDao;
	}
	
	//保存单个对象
	public int saveObject(T object) {
		//参数校验
		if(object==null)
		throw new IllegalArgumentException("异常的数据");
		//执行保存
		int rows = baseDao.saveObject(object);
		//合法性验证
		if(rows==0)
		throw new ServiceException("保存失败");
		//处理结果
		return rows;
	}
	@Override
	public List<T> findObjects(Integer id) {
		if(id==null||id==0)
		throw new IllegalArgumentException();
		List<T> objs = baseDao.findObjects(id);
		if(objs==null||objs.size()==0)
			throw new ServiceException("记录可能不存在");
		return objs;
	}
	
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id==0)
		throw new IllegalArgumentException("异常数据");
		int row = baseDao.deleteObject(id);
		if(row==0)
			throw new ServiceException("记录可能不存在");
		return row;
	}
	
	@Override
	public int updateObject(T object) {
		if(object==null)
			throw new IllegalArgumentException("异常数据");
		int row = baseDao.updateObject(object);
		if(row==0)
			throw new ServiceException("用户可能不存在");
		return row;
	}

}
