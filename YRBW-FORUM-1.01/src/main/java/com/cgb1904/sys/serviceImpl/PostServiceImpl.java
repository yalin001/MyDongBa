package com.cgb1904.sys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgb1904.common.exception.ServiceException;
import com.cgb1904.sys.dao.PostDao;
import com.cgb1904.sys.po.Post;
import com.cgb1904.sys.service.PostService;
import com.cgb1904.common.vo.PageObject;
@Service
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService{

	private PostDao postDao;
	@Autowired
	public PostServiceImpl(PostDao postDao) {
		super(postDao);
		this.postDao=postDao;
	}

	public Post findPost(Integer id) {
		if(id==null||id==0)
			throw new IllegalArgumentException();
		Post post = postDao.findPost(id);
		if(post==null)
			throw new ServiceException("记录可能不存在");
		return post;
	}

	
	/**
	 * 分页查询帖子
	 */
	@Override
	public PageObject<Post> findPageObjects(
			String title, Integer pageCurrent) {
		//1.验证参数合法性
		//1.1验证pageCurrent的合法性，
		//不合法抛出IllegalArgumentException异常
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		//2.基于条件查询总记录数
		//2.1) 执行查询
		int rowCount=postDao.getRowCount(title);
		//2.2) 验证查询结果，假如结果为0不再执行如下操作
		if(rowCount==0)
			throw new ServiceException("系统没有查到对应记录");
		//3.基于条件查询当前页记录(pageSize定义为2)
		//3.1)定义pageSize
		int pageSize=10;
		//3.2)计算startIndex
		int startIndex=(pageCurrent-1)*pageSize;
		//3.3)执行当前数据的查询操作
		List<Post> records=
				postDao.findPageObjects(title, startIndex, pageSize);
		//4.对分页信息以及当前页记录进行封装
		//4.1)构建PageObject对象
		PageObject<Post> pageObject=new PageObject<>();
		//4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		//5.返回封装结果。
		return pageObject;
	}

		//保存用户发帖
		//	@Override
		//	public int savePost(Post post) {
		//		//1.参数校验
		//		if(post==null
		//				||post.getId()==null
		//				||post.getBlockId()==null
		//				||post.getAuthorUsername()==null
		//				||post.getContent()==null
		//				||post.getTitle()==null
		//				||post.getType()==null)
		//			throw new IllegalArgumentException("异常的数据");
		//		//2.执行方法
		//		int rows = postDao.savePost(post);
		//		//3.返回结果
		//		return rows;
		//	}
		//	@Override
		//	public List<Post> findPosts(Integer id) {
		//		if(id==null||id==0)
		//			throw new IllegalArgumentException("id不能为空");
		//		List<Post> posts = postDao.findPosts(id);
		//		return posts;
		//	}
}
