package com.app.dao.comment;

import java.util.List;

import com.app.dto.comment.Comment;

public interface CommentDAO {
	List<Comment> findCommentList();
	
	int saveComment(Comment comment);

	Comment findCommentById(int id);

	int removeComment(int id);

	int modifyComment(Comment comment);
}
