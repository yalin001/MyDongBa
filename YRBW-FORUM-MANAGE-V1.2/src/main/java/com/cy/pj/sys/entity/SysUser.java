package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
private Integer id;
private String username;
private String password;
private String salt;//盐值
private String email;
private Integer valid=1;
private String mobile;
private Date registerTime;
private Date modifiedTime;
private String modifiedUser;

}
