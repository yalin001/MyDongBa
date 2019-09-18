package com.cgb1904.sys.service;

import com.cgb1904.sys.po.SysLog;

public interface SysLogService extends PageService<SysLog>{
	
	   
	   /**
	    * 基于id删除日志信息
	    * @param ids
	    * @return
	    */
	   int deleteObjects(Integer... ids);
	  

}







