package com.cgb1904.sys.po;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 回复贴
 * @author Tarena
 *
 */
@Data
public class Comment {

	//评论id 
	private Integer id;
	//发帖人姓名
	private String postingUsername;
	//回复的内容
	private String content;
	//地址
	private String address;
	//浏览器
	private String browse;
	//操作系统
	private String osname;
	//评论时间
	private Date createdTime;
	//评论作者
	private String authorUsername;
	
	
}
