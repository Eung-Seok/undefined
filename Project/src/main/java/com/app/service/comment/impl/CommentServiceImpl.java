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
}
