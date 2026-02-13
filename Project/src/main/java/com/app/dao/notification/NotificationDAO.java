package com.app.dao.notification;

import java.util.List;

import com.app.dto.notification.Notification;

public interface NotificationDAO {
	List<Notification> findNotificationList();
}
