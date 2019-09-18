package com.cgb1904.sys.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysComment implements Serializable{
private Integer id;
private String img;
private String postingUsername;
private String content;
private String address;
private String browse;
private String osname;
private Date createdTime;
private String createdUser;

}
