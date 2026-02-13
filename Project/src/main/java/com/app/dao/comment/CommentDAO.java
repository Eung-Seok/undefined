package com.app.dao.comment;

import java.util.List;

import com.app.dto.comment.Comment;

public interface CommentDAO {
	List<Comment> findCommentList();
}
