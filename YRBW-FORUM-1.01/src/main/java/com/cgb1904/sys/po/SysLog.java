package com.cgb1904.sys.po;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * POJO:普通的java对象
 * 1)PO(持久化对象):实现与数据库表记录之间的映射
 * 2)VO(值对象):封装数据
 * 3)...
 * @author Administrator
 */
@Data
public class SysLog implements Serializable{
	private static final long serialVersionUID = -1479538995581206701L;
	private Integer id;
	private String username;
	private String operation;
	/**执行的方法*/
	private String method;
	/**执行方法时传入的参数*/
	private String params;
	private Long  time;
	private String ip;
	private Date createdTime;
	
	
}
