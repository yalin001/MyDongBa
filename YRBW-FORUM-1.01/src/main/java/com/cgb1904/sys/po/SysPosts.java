package com.cgb1904.sys.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
@Data //Sys类在编译时会自动生成set/get写到.class文件
@ToString
@Accessors(chain = true)//提供一个set链接
public class SysPosts implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	//用户名
	private Integer blockId;
	// 版本id
	private String authorusername;
	//用户操作
	private String title;
	//请求方法
	private String type;
	//请求参数
	private String content;
	//创建时间
	private Date createdTime;
	//创建时间
	private Date modifiedTime;


}
