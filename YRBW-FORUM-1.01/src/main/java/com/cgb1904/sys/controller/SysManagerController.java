package com.cgb1904.sys.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgb1904.common.vo.JsonResult;
import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.SysManager;
import com.cgb1904.sys.service.SysManagerService;

@Controller
@RequestMapping("/manager/")
public class SysManagerController {
	
	@Autowired
	private SysManagerService sysManagerService;
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
		String username,Integer pageCurrent){
		PageObject<SysManager> pageObject=
		sysManagerService.findPageObjects(username,
pageCurrent);
		return new JsonResult(pageObject);
	}

	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysManager entity){
		sysManagerService.saveObject(entity);
		return new JsonResult("save ok");
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		Map<String,Object> map=
		sysManagerService.findObjectById(id);
		return new JsonResult(map);
	}	

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
	    SysManager entity,Integer[] roleIds){
		sysManagerService.updateObject(entity);
		return new JsonResult("update ok");
	}

	 @RequestMapping("doPwdEditUI")
	 public String doUserEditUI() {
		 return "sys/pwd_edit";
	 }
	 
	 @RequestMapping("doLogin")
	   @ResponseBody
	   public JsonResult doLogin(String username,String password,boolean isRememberMe ){
		   //1.获取Subject对象
		   Subject subject=SecurityUtils.getSubject();
		   //2.通过Subject提交用户信息,交给shiro框架进行认证操作
		   //2.1对用户进行封装
		   UsernamePasswordToken token=
		   new UsernamePasswordToken(
				   username,//身份信息
				   password);//凭证信息
		   if(isRememberMe) {
				token.setRememberMe(true); 
			 }

		   //2.2对用户信息进行身份认证
		   subject.login(token);
		   System.out.println(token+"123");
		   //分析:
		   //1)token会传给shiro的SecurityManager
		   //2)SecurityManager将token传递给认证管理器
		   //3)认证管理器会将token传递给realm
		   return new JsonResult("login ok");
	   }

	 @RequestMapping("doUpdatePassword")
	 @ResponseBody
	 public JsonResult doUpdatePassword(
			 String pwd,
			 String newPwd,
			 String cfgPwd) {
		 sysManagerService.updatePassword(pwd, newPwd, cfgPwd);
		 return new JsonResult("update ok");
	 }

}
