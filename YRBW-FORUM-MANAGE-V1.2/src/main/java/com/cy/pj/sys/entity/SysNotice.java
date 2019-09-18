package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class SysNotice implements Serializable {
private Integer id;
private String username;
private String content;
private String createdManager;
private Date createdTime;


}
