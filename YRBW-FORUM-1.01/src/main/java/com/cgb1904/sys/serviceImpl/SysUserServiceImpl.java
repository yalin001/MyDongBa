package com.cgb1904.sys.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cgb1904.common.exception.ServiceException;
import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.dao.SysUserDao;
import com.cgb1904.sys.po.SysUser;
import com.cgb1904.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public PageObject<SysUser> findPageObjects(String username, Integer pageCurrent) {
	//1.数据合法性验证
			if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("参数不合法");
	//2.依据条件获取总记录数
			int rowCount=sysUserDao.getRowCount(username);
			System.out.println(rowCount);
	        if(rowCount==0)
			throw new ServiceException("记录不存在");
			//3.计算startIndex的值
			int pageSize=3;
			int startIndex=(pageCurrent-1)*pageSize;
			//4.依据条件获取当前页数据
			List<SysUser> records=sysUserDao.findPageObjects(
			username, startIndex, pageSize);
			//5.封装数据
			PageObject<SysUser> pageObject=new PageObject<>();
			pageObject.setPageCurrent(pageCurrent);
			pageObject.setRowCount(rowCount);
			pageObject.setPageSize(pageSize);
			pageObject.setRecords(records);
	pageObject.setPageCount((rowCount-1)/pageSize+1);
			return pageObject;

	}
	@Override
	public int validById(Integer id, Integer valid) {
		//1.合法性验证
				if(id==null||id<=0)
				throw new ServiceException("参数不合法,id="+id);
				if(valid!=1&&valid!=0)
				throw new ServiceException("参数不合法,valie="+valid);
				//2.执行禁用或启用操作
				int rows=0;
				try{
			    rows=sysUserDao.validById(id, valid);
				}catch(Throwable e){
				e.printStackTrace();
				//报警,给维护人员发短信
				throw new ServiceException("底层正在维护");
				}
				//3.判定结果,并返回
				if(rows==0)
				throw new ServiceException("此记录可能已经不存在");
				return rows;

	}
	@Override
	public int saveObject(SysUser entity) {
		//1.验证数据合法性
				if(entity==null)
				throw new ServiceException("保存对象不能为空");
				if(StringUtils.isEmpty(entity.getUsername()))
			    throw new ServiceException("用户名不能为空");
				if(StringUtils.isEmpty(entity.getPassword()))
				throw new ServiceException("密码不能为空");
				//2.将数据写入数据库
				String salt=UUID.randomUUID().toString();
				entity.setSalt(salt);
				//加密(先了解,讲shiro时再说)
				SimpleHash sHash=
			    new SimpleHash("MD5",entity.getPassword(), salt);
				entity.setPassword(sHash.toHex());
				int rows=sysUserDao.insertObject(entity);
				//3.返回结果
				return rows;

	}
	@Override
	public SysUser findObjectById(Integer userId) {
		//1.合法性验证
				if(userId==null||userId<=0)
				throw new ServiceException(
				"参数数据不合法,userId="+userId);
				//2.业务查询
				SysUser user=
				sysUserDao.findObjectById(userId);
				if(user==null)
				throw new ServiceException("此用户已经不存在");
				return user;

	}
	@Override
	public int updateObject(SysUser entity) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		//其它验证自己实现，例如用户名已经存在，密码长度，...
		//2.更新用户自身信息
		int rows=sysUserDao.updateObject(entity);
		//4.返回结果
		return rows;

	}

}
