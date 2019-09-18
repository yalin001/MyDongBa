package com.cgb1904.sys.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgb1904.sys.dao.ReplyDao;
import com.cgb1904.sys.po.Reply;
import com.cgb1904.sys.service.ReplyService;

public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService{

	private ReplyDao replyDao;
	@Autowired
	public ReplyServiceImpl(ReplyDao replyDao) {
		super(replyDao);
		this.replyDao=replyDao;
	}
	

	
}
