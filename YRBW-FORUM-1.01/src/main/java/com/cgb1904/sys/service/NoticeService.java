package com.cgb1904.sys.service;

import com.cgb1904.sys.po.Notice;

public interface NoticeService extends BaseService<Notice>{

	//查询最新的公告
		Notice findLatestNotice();
}
