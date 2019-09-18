package com.cgb1904.sys.po;

import java.util.Date;

import lombok.Data;

@Data
public class SysComments {
private Integer id;
private String img;
private String postingUsername;
private String content;
private String address;
private String browse;
private String osname;
private String createdUser;
private Date createdTime;
}
