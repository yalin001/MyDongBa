package com.cgb1904.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgb1904.common.vo.JsonResult;
import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.SysPosts;
import com.cgb1904.sys.service.SysPostsService;

@RestController
@RequestMapping("/posts/")
public class SysPostsController {
	
	@Autowired
	private SysPostsService sysPostsService;
	
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
