package com.cgb1904.sys.po;

import java.util.Date;

import lombok.Data;
/**
 * 帖子对象
 * @author Tarena
 *
 */
@Data
public class Post {
	//帖子id
	private Integer id;
	//板块id
	private Integer blockId;
	//作者
	private String authorUsername;
	//标题
	private String title;
	//主题类型
	private String type;
	//发帖内容
	private String content;
	//创建时间
	private Date createdTime;
	//修改时间
	private Date modifiedTime;
	//回复数
	private Integer replyNumbers;
	
}
