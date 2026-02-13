package com.app.service.notification;

import java.util.List;

import com.app.dto.notification.Notification;

public interface NotificationService {
	List<Notification> findNotificationList();
}
