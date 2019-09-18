package com.cgb1904.sys.po;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	//id
	private Integer id;
	//内容
	private String content;
	//地址
	private String address;
	//浏览器
	private String browse;
	//操作系统
	private String osname;
	//回复评论时间
	private Date createdTime;
	//回复人
	private String createdUser;
	//评论的人
	private String commentUser;
	//评论的id
	private Integer commentId;
	
	

}
