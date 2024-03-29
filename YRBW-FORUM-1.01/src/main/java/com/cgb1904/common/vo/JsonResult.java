package com.cgb1904.common.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonResult implements Serializable{
	private static final long serialVersionUID = -4138033536625725437L;
	/**状态码*/
	private int state=1;//1 表示ok,0表示error
	/**状态码对应的消息*/
	private String message="ok";
	/**页面上要具体呈现的数据*/
	private Object data;
	public int getState() {
		return state;
	}
	public JsonResult() {
	}
	public JsonResult(String message) {
		this.message=message;
	}
	public JsonResult(Object data) {
		this.data=data;
	}
	public JsonResult(Throwable e) {
		this.state=0;
		this.message=e.getMessage();
	}
}
