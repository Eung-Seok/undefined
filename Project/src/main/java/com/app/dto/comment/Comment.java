package com.app.dto.comment;

import lombok.Data;

@Data
public class Comment {
	int id;
	int postId;
	int authorUserId;
	int parentCommentId;
	String content;
	String createdAt;
	String updatedAt;
}
