package com.app.service.notification;

import java.util.List;

import com.app.dto.notification.Notification;

public interface NotificationService {
	List<Notification> findNotificationList();

	int saveNotification(Notification notification);

	Notification findNotificationById(int id);

	int removeNotification(int id);

	int modifyNotification(Notification notification);
}
