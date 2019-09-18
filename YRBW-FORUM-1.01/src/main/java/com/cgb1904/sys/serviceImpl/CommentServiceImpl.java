package com.cgb1904.sys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cgb1904.sys.dao.CommentDao;
import com.cgb1904.sys.po.Comment;
import com.cgb1904.sys.service.CommentService;
import com.cgb1904.common.exception.ServiceException;
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

	private  CommentDao commentDao;
	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		super(commentDao);
		this.commentDao=commentDao;
	}
	@Override
	public List<Comment> findCommentsByPostAuthor(String authorName) {
		if(StringUtils.isEmpty(authorName))
		throw new IllegalArgumentException();
		List<Comment> lists = commentDao.findCommentsByPostAuthor(authorName);
		if(lists==null||lists.size()==0)
			throw new ServiceException("记录可能不存在");
		return lists;
	}

//	@Override
//	public List<Comment> findReplies(Integer id) {
//		//参数校验
//		if(id==null||id==0)
//		throw new IllegalArgumentException("id不能为空");
//		//执行查询
//		List<Comment> replies = commentDao.findReplies(id);
//		//返回结果
//		return replies;
//	}
	
	

}
