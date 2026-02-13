package com.app.service.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.comment.CommentDAO;
import com.app.dto.comment.Comment;
import com.app.service.comment.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDAO commentDao;

	@Override
	public List<Comment> findCommentList() {
		List<Comment> commentList = commentDao.findCommentList();
		return commentList;
	}

	@Override
	public int saveComment(Comment comment) {
		int result = commentDao.saveComment(comment);
		return result;
	}

	@Override
	public Comment findCommentById(int id) {
		Comment comment = commentDao.findCommentById(id);
		return comment;
	}

	@Override
	public int removeComment(int id) {
		int result = commentDao.removeComment(id);
		return result;
	}

	@Override
	public int modifyComment(Comment comment) {
		int result = commentDao.modifyComment(comment);
		return result;
	}
}
