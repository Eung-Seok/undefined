package com.app.dto.post;

import lombok.Data;

@Data
public class Post {
	int id;
	int boardId;
	int authorUserId;
	String title;
	String content;
	String createdAt;
	String updatedAt;
}
