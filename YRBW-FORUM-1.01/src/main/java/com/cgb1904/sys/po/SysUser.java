package com.cgb1904.sys.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysUser implements Serializable{

	private static final long serialVersionUID = -1616220225033094946L;
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String mobile;
	private Integer valid;
	private Date registerTime;
	private String modifiedUser;
	private Date modifiedTime;
}
