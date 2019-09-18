package com.cgb1904.sys.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cgb1904.common.exception.ServiceException;
import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.dao.SysManagerDao;
import com.cgb1904.sys.po.SysManager;
import com.cgb1904.sys.service.SysManagerService;

@Service
public class SysManagerServiceImpl implements SysManagerService{
	
	@Resource
	private SysManagerDao sysManagerDao;
	
	@Override
	public PageObject<SysManager> findPageObjects(String username, 
			Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
		throw new ServiceException("参数不合法");
//2.依据条件获取总记录数
		int rowCount=sysManagerDao.getRowCount(username);
        if(rowCount==0)
		throw new ServiceException("记录不存在");
		//3.计算startIndex的值
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		//4.依据条件获取当前页数据
		List<SysManager> records=sysManagerDao.findPageObjects(
		username, startIndex, pageSize);
		//5.封装数据
		PageObject<SysManager> pageObject=new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
        pageObject.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}
@Override
public int saveObject(SysManager entity) {
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
		
		 SimpleHash sHash= new SimpleHash("MD5",entity.getPassword(), salt);
		  entity.setPassword(sHash.toHex());
		

			int rows=sysManagerDao.insertObject(entity);
			//3.返回结果
			return rows;

}
@Override
public Map<String, Object> findObjectById(Integer managerId) {
	//1.合法性验证
			if(managerId==null||managerId<=0)
			throw new ServiceException(
			"参数数据不合法,managerId="+managerId);
			//2.业务查询
			SysManager manager=
			sysManagerDao.findObjectById(managerId);
			if(manager==null)
			throw new ServiceException("此用户已经不存在");
			//3.数据封装
			Map<String,Object> map=new HashMap<>();
			map.put("manager", manager);
			return map;

}
@Override
public int updateObject(SysManager entity) {
	//1.参数有效性验证
			if(entity==null)
				throw new IllegalArgumentException("保存对象不能为空");
			if(StringUtils.isEmpty(entity.getUsername()))
				throw new IllegalArgumentException("用户名不能为空");
			//其它验证自己实现，例如用户名已经存在，密码长度，...
			//2.更新用户自身信息
			int rows=sysManagerDao.updateObject(entity);
			//4.返回结果
			return rows;

}
@Override
public int updatePassword(String password, String newPassword, String cfgPassword) {
	//1.判定新密码与密码确认是否相同
			if(StringUtils.isEmpty(newPassword))
			throw new IllegalArgumentException("新密码不能为空");
			if(StringUtils.isEmpty(cfgPassword))
			throw new IllegalArgumentException("确认密码不能为空");
			if(!newPassword.equals(cfgPassword))
			throw new IllegalArgumentException("两次输入的密码不相等");
			//2.判定原密码是否正确
			if(StringUtils.isEmpty(password))
			throw new IllegalArgumentException("原密码不能为空");
			//获取登陆用户
			SysManager manager=(SysManager)SecurityUtils.getSubject().getPrincipal();
//			if(!manager.getUsername().equals("root"))
//				throw new ServiceException("您并没有此项权限");	
			SimpleHash sh=new SimpleHash("MD5",
			password, manager.getSalt(), 1);
			if(!manager.getPassword().equals(sh.toHex()))
			throw new IllegalArgumentException("原密码不正确");
			//3.对新密码进行加密
			String salt=UUID.randomUUID().toString();
			sh=new SimpleHash("MD5",newPassword,salt, 1);
			//4.将新密码加密以后的结果更新到数据库
			int rows=sysManagerDao.updatePassword(sh.toHex(), salt,manager.getId());
			if(rows==0)
			throw new ServiceException("修改失败");
			return rows;

		
		

}

}
