package com.cgb1904.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cgb1904.sys.po.Comment;

/**
 *回帖模块
 * @author Tarena
 *
 */
@Mapper
public interface CommentDao extends BaseDao<Comment>{
//	//保存用户评论
//	int saveReply(Comment comment);//封装到BaseDao
	//用户评论记录
//	List<Comment> findReplies(Integer id);
	
	List<Comment> findCommentsByPostAuthor(String authorName);

}
