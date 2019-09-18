package com.cgb1904.sys.service;

import java.util.List;

import com.cgb1904.sys.po.Comment;

public interface CommentService extends BaseService<Comment>{

//	//保存用户评论
//	int saveComment(Comment comment);//封装到BaseService
	//用户个人中心评论记录
//	List<Comment> findComments(Integer id);
	//帖子页面显示评论
	List<Comment> findCommentsByPostAuthor(String authorName);
}
