package com.cgb1904.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgb1904.common.exception.ServiceException;
import com.cgb1904.common.vo.JsonResult;
import com.cgb1904.common.vo.PageObject;
import com.cgb1904.sys.po.Post;
import com.cgb1904.sys.po.SysUser;
import com.cgb1904.sys.service.PostService;

@Controller
@RequestMapping("/post/")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping("doPost")
	@ResponseBody
	public JsonResult doSavePost(Post entity) {
		Subject subject=SecurityUtils.getSubject();
		SysUser user = (SysUser)subject.getPrincipal();
		if(user==null) {
			JsonResult result = new JsonResult("请登录后再发帖！！");
			result.setState(0);
			return result;
		}
		if(user.getValid()==0) {
			JsonResult result = new JsonResult("你已被禁止发帖！！");
			result.setState(0);
			return result;
		}
		entity.setAuthorUsername(user.getUsername());
		postService.saveObject(entity);
		return new JsonResult("save ok");
	}

	@RequestMapping("doFindPost")
	@ResponseBody
	public JsonResult doFindPost(Integer id) {
		Post post = postService.findPost(id);
		return new JsonResult(post);
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String title,Integer pageCurrent){
		PageObject<Post> pageObject=
				postService.findPageObjects(title,pageCurrent);
		return new JsonResult(pageObject);
	}
	
	@RequestMapping("doWatchPost")
	@ResponseBody
	public JsonResult doWatchArticles(Integer id,String username) {
		
		return new JsonResult();
	}
}
