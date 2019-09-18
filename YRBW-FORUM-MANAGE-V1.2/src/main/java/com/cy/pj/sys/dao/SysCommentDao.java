package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysComment;
@Mapper
public interface SysCommentDao {
	List<SysComment> findPageObjects(
		      @Param("content")String  content,
		      @Param("startIndex")Integer startIndex,
		      @Param("pageSize")Integer pageSize);

int getRowCount(@Param("content") String content);

int deleteObjects(@Param("ids")Integer...ids);

}
