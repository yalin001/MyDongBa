package com.cgb1904.sys.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cgb1904.common.exception.ServiceException;
import com.cgb1904.sys.dao.NoticeDao;
import com.cgb1904.sys.po.Notice;
import com.cgb1904.sys.service.NoticeService;

@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements NoticeService{

	private NoticeDao noticeDao;
	public NoticeServiceImpl(NoticeDao noticeDao) {
		super(noticeDao);
		this.noticeDao=noticeDao;
	}
	@Override
	public int saveObject(Notice object) {
		if(object==null)
		throw new IllegalArgumentException("数据异常");
		int row = noticeDao.saveObject(object);
		if(row==0)
		throw new ServiceException("保存失败");
		return row;
	}
	@Override
	public List<Notice> findObjects(Integer id) {
		if(id==null||id==0)
			throw new IllegalArgumentException("数据异常");
		List<Notice> lists = noticeDao.findObjects(id);
		if(lists==null||lists.size()==0)
			throw new ServiceException("查询失败，可能没有记录");
		return lists;
	}
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id==0)
			throw new IllegalArgumentException("数据异常");
		int row = noticeDao.deleteObject(id);
		if(row==0)
			throw new ServiceException("删除失败,可能没有数据");
		return row;
	}
	@Override
	public int updateObject(Notice object) {
		if(object==null)
			throw new IllegalArgumentException("数据异常");
		int row = noticeDao.updateObject(object);
		if(row==0)
			throw new ServiceException("修改失败，可能没有数据");
		return row;
	}
	
	
	//查询最新的公告，用于显示
	@Override
	public Notice findLatestNotice() {
		Notice notice = noticeDao.findLatestNotice();
		if(notice==null)
			throw new ServiceException("似乎没有公告");
		return notice;
	}


}
