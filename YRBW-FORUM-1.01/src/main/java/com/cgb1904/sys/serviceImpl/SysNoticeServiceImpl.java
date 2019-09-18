package com.cgb1904.sys.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cgb1904.common.exception.ServiceException;
import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.dao.SysNoticeDao;
import com.cgb1904.sys.po.SysNotice;
import com.cgb1904.sys.service.SysNoticeService;

@Service
public class SysNoticeServiceImpl implements SysNoticeService{
	
	@Resource
	private SysNoticeDao sysNoticeDao;
	
	@Override
	public PageObject<SysNotice> findPageObjects(String username, Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
		throw new ServiceException("参数不合法");
//2.依据条件获取总记录数
		int rowCount=sysNoticeDao.getRowCount(username);
        if(rowCount==0)
		throw new ServiceException("记录不存在");
		//3.计算startIndex的值
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		//4.依据条件获取当前页数据
		List<SysNotice> records=sysNoticeDao.findPageObjects(
		username,startIndex, pageSize);
		//5.封装数据
		PageObject<SysNotice> pageObject=new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
pageObject.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;

}
@Override
public int saveObject(SysNotice entity) {
	//1.验证数据合法性
			if(entity==null)
			throw new ServiceException("保存对象不能为空");
			if(StringUtils.isEmpty(entity.getUsername()))
		    throw new ServiceException("用户名不能为空");
			if(StringUtils.isEmpty(entity.getContent()))
			throw new ServiceException("公告内容不能为空");
			// 将数据写入数据库
			int rows=sysNoticeDao.insertObject(entity);
			//3.返回结果
			return rows;

}
@Override
public int deleteObjects(Integer... ids) {
	//1.判定参数合法性
			if(ids==null||ids.length==0)
		    throw new IllegalArgumentException("请选择一个");
			//2.执行删除操作
			int rows;
			try{
			rows=sysNoticeDao.deleteObjects(ids);
			}catch(Throwable e){
			e.printStackTrace();
			//发出报警信息(例如给运维人员发短信)
			throw new ServiceException("系统故障，正在恢复中...");
			}
			//4.对结果进行验证
			if(rows==0)
			throw new ServiceException("记录可能已经不存在");
			//5.返回结果
			return rows;

}

}
