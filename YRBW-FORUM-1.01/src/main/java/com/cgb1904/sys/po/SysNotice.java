package com.cgb1904.sys.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class SysNotice implements Serializable {
private Integer id;
private String username;
private String content;
private String createdManager;
private Date createdTime;


}
