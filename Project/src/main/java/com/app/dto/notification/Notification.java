package com.app.dto.notification;

import lombok.Data;

@Data
public class Notification {
	int id;
	int userId;
	String type;
	String message;
	String linkUrl;
	String isRead;
	String createdAt;
}
