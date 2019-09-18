package com.cgb1904.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgb1904.sys.po.SysUser;
import com.cgb1904.sys.service.UserService;
import com.cgb1904.common.vo.JsonResult;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("doUpdatePassword")
	@ResponseBody
	public JsonResult doUpdatePassword(
			String pwd,
			String newPwd,
			String cfgPwd) {
		userService.updatePassword(pwd, newPwd, cfgPwd);
		return new JsonResult("update ok");
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysUser entity){
		userService.saveObject(entity);
		return new JsonResult("save ok");
	}
}
