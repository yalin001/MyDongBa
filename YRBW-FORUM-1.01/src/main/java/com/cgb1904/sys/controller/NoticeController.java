package com.cgb1904.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgb1904.common.vo.JsonResult;
import com.cgb1904.sys.po.Notice;
import com.cgb1904.sys.service.NoticeService;

@RestController
@RequestMapping("/notice/")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("doFindLatestNotice")
	public JsonResult doFindLatestNotice() {
		Notice notice = noticeService.findLatestNotice();
		return new JsonResult(notice);
	}
	
//	@RequestMapping("doSaveNotices")
//	public JsonResult doSaveNotices(Notice notice) {
//		int row = noticeService.saveObject(notice);
//		return new JsonResult(row);
//	}
//	@RequestMapping("doFindNotices")
//	public JsonResult doFindNotices(Integer id){
//		List<Notice> lists = noticeService.findObjects(id);
//		return new JsonResult(lists);
//	}
//	@RequestMapping("doDeleteNotice")
//	public JsonResult doDeleteNotice(Integer id) {
//		int row = noticeService.deleteObject(id);
//		return new JsonResult(row);
//	}
//	@RequestMapping("doUpdateNotice")
//	public JsonResult doUpdateNotice(Notice notice) {
//		int row = noticeService.updateObject(notice);
//		return new JsonResult(row);
//	}
	
}
