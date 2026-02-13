package com.app.service.comment;

import java.util.List;

import com.app.dto.comment.Comment;

public interface CommentService {
	List<Comment> findCommentList();
}
