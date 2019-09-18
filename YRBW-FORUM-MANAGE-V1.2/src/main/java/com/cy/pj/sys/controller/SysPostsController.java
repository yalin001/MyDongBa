package com.cy.pj.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysPosts;
import com.cy.pj.sys.service.SysPostsService;
@RestController
@RequestMapping("/posts/")
public class SysPostsController {
	@Autowired
	SysPostsService sysPostsService;
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String author_username,Integer pageCurrent){
	 PageObject<SysPosts> pageObject=
		sysPostsService.findPageObjects(author_username,pageCurrent);
	 System.out.println(pageObject);
	return new JsonResult(pageObject);
	}
	 @RequestMapping("doDeleteObjects")
	 @ResponseBody
	  public JsonResult doDeleteObjects(Integer...ids){
		  sysPostsService.deleteObjects(ids);
		  return new JsonResult("delete ok");
	  }
}
