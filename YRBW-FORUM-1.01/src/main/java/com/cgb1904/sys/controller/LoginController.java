package com.cgb1904.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgb1904.common.vo.JsonResult;
import com.cgb1904.sys.po.SysUser;

@RestController
@RequestMapping("/login/")
public class LoginController {

	@RequestMapping("doLogin")
	public JsonResult doLogin(
			String username,
			String password,
			boolean isRememberMe) {
		//1.获取Subject对象
		Subject subject=SecurityUtils.getSubject();
		//2.提交用户信息
		UsernamePasswordToken token=new UsernamePasswordToken();
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		if(isRememberMe)
			token.setRememberMe(true);
		subject.login(token);//提交谁SecurityManager
		return new JsonResult("login ok");
	}
	
	@RequestMapping("doLoginAndSuccess")
	public JsonResult doLoginAndSuccess() {
		Subject subject=SecurityUtils.getSubject();
		SysUser user = (SysUser)subject.getPrincipal();
		return new JsonResult(user);
	}
}

