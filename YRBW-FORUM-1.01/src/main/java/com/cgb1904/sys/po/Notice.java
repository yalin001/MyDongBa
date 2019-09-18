package com.cgb1904.sys.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class Notice implements Serializable{

	private static final long serialVersionUID = 3682898803389508979L;

	//id 
	private Integer id;
	//公告名
	private String username;
	//内容
	private String content;
	//创建时间
	private Date createdTime;
	//创建管理员
	private String createdManager;
	
}
