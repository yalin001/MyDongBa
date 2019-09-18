package com.cgb1904.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgb1904.common.vo.JsonResult;
import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.SysComment;
import com.cgb1904.sys.service.SysCommentService;

@Controller
@RequestMapping("/comment/")
public class SysCommentController {
	@Autowired	
	private SysCommentService sysCommentService;
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String content,Integer pageCurrent){
	 PageObject<SysComment> pageObject=
		sysCommentService.findPageObjects(content,pageCurrent);
	return new JsonResult(pageObject);
	}

	 @RequestMapping("doDeleteObjects")
	  @ResponseBody
	  public JsonResult doDeleteObjects(Integer...ids){
		  sysCommentService.deleteObjects(ids);
		  return new JsonResult("delete ok");
	  }

}
