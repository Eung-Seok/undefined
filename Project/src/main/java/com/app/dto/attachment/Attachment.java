package com.app.dto.attachment;

import lombok.Data;

@Data
public class Attachment {
	int id;
	int uploaderUserId;
	Integer postId;
	Integer taskId;
	String fileName;
	String originalFileName;
	String fileUrl;
	String createdAt;
}
