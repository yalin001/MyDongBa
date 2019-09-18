package com.cy.pj.sys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/")
@Controller
public class PageController {
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
			return "login";
	}
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
