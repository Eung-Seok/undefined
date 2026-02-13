package com.app.dto.attachment;

import lombok.Data;

@Data
public class Attachment {
	int id;
	int uploaderUserId;
	int postId;
	int taskId;
	String fileName;
	String originalFileName;
	String fileUrl;
	String createdAt;
}
