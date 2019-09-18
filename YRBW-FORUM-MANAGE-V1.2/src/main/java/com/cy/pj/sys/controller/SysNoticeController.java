package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysNotice;
import com.cy.pj.sys.service.SysNoticeService;
@Controller
@RequestMapping("/notice/")
public class SysNoticeController {
	@Autowired
	SysNoticeService sysNoticeService;
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
		String username,Integer pageCurrent){
		PageObject<SysNotice> pageObject=
		sysNoticeService.findPageObjects(username,pageCurrent);
		return new JsonResult(pageObject);
	}
//	@RequestMapping("doNoticeEditUI")
//	public String doUserEditUI(){
//		return "sys/notice_edit";
//	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysNotice entity){
		System.out.println(entity.getContent());
		sysNoticeService.saveObject(entity);
		return new JsonResult("save ok");
	}

	 @RequestMapping("doDeleteObjects")
	  @ResponseBody
	  public JsonResult doDeleteObjects(Integer...ids){
		  sysNoticeService.deleteObjects(ids);
		  return new JsonResult("delete ok");
	  }

}
