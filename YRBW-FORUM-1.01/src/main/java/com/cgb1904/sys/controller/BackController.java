package com.cgb1904.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back/")
public class BackController {

	@RequestMapping("doBackLoginUI")
	public String doBackLoginUI() {
		return "sys/back_login";
	}

	@RequestMapping("doBackIndexUI")
	public String doBackLoginSuccess() {
		return "sys/back_starter";
	}
}
