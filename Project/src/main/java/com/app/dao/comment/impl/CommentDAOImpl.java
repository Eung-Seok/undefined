package com.app.dao.comment.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.comment.CommentDAO;
import com.app.dto.comment.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Comment> findCommentList() {
		List<Comment> commentList = sqlSessionTemplate.selectList("comment_mapper.findCommentList");
		return commentList;
	}
	
}
