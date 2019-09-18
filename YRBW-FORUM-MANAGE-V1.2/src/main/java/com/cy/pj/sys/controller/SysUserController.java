package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;


@Controller
@RequestMapping("/user/")
public class SysUserController {
	@Autowired
	SysUserService sysUserService;
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
		String username,Integer pageCurrent){
		PageObject<SysUser> pageObject=
		sysUserService.findPageObjects(username,
pageCurrent);
		return new JsonResult(pageObject);
	}

	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id,Integer valid){
		   sysUserService.validById(id,valid);
		return new JsonResult("update ok");
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysUser entity){
		sysUserService.saveObject(entity);
		return new JsonResult("save ok");
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		SysUser sysUser=
		sysUserService.findObjectById(id);
		return new JsonResult(sysUser);
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
	    SysUser entity){
		sysUserService.updateObject(entity);
		return new JsonResult("update ok");
	}

}
