package com.cgb1904.sys.service;

import com.cgb1904.sys.po.Post;
import com.cgb1904.common.vo.PageObject;

/**
 * 帖子模块
 * @author Tarena
 *
 */
public interface PostService extends BaseService<Post>{
//	//保存用户发布的帖子
//	int savePost(Post post);
	//个人空间查询发帖记录
//	List<Post> findPosts(Integer id);
	
	//基于帖子id查询用户发帖记录 用于个人中心页面
	Post findPost(Integer id);
	
	  /**
	   * 通过此方法实现分页查询操作
     * @param name 基于条件查询时的参数名
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
	 PageObject<Post> findPageObjects(
			 String title,
			 Integer pageCurrent);
	 
}
