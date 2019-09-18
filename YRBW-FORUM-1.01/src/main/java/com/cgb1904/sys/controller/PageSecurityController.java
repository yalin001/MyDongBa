package com.cgb1904.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 需要登录才可以跳转的页面
 * @author Tarena
 *
 */
@Controller
public class PageSecurityController {

	@RequestMapping("doLoginSuccess")
	public String doLoginSuccess() {
		return "success";
	}
}
