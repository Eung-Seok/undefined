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

	@Override
	public int saveComment(Comment comment) {
		int result = sqlSessionTemplate.insert("comment_mapper.saveComment", comment);
		return result;
	}

	@Override
	public Comment findCommentById(int id) {
		Comment comment = sqlSessionTemplate.selectOne("comment_mapper.findCommentById",id);
		return comment;
	}

	@Override
	public int removeComment(int id) {
		int result = sqlSessionTemplate.delete("comment_mapper.removeComment", id);
		return result;
	}

	@Override
	public int modifyComment(Comment comment) {
		int result = sqlSessionTemplate.update("comment_mapper.modifyComment", comment);
		return result;
	}
	
}
