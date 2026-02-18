package com.app.dao.notification;

import java.util.List;

import com.app.dto.notification.Notification;

public interface NotificationDAO {
	List<Notification> findNotificationList();
	
	int saveNotification(Notification notification);

	Notification findNotificationById(int id);

	int removeNotification(int id);

	int modifyNotification(Notification notification);
}
