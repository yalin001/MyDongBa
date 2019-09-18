package com.cgb1904.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "index";
	}

	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}

	@RequestMapping("doLoginSuccessing")
	public String doLoginSuccessing() {
		return "successing";
	}

	@RequestMapping("doRegisterUI")
	public String doRegisterUI() {
		return "register";
	}

	@RequestMapping("doPublishUI")
	public String doPublishUI() {
		return "publish";
	}

	@RequestMapping("doConnectUI")
	public String doConnectUI() {
		return "connect";
	}
	
	@RequestMapping("doReplyUI")
	public String doReplyUI() {
		return "reply/index";
	}
	
	@RequestMapping("doArticleUI")
	public String doArticleUI() {
		return "article/article";
	}
	
	//后台管理模块
	@RequestMapping("{module}/{page}")
	public String doModuleUI(//rest
			@PathVariable String page) {
		return "sys/"+page;
	}
	
	@RequestMapping("doUserEditUI")
	public String doUserEditUI(){
		return "sys/user_edit";
	}
	@RequestMapping("doUserListUI")
	 public String doUserListUI(){
		System.out.println("已经跳转sys/user_list");
		return "sys/user_list";
	}
}
