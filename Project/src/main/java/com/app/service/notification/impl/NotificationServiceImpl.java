package com.app.service.notification.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.notification.NotificationDAO;
import com.app.dto.notification.Notification;
import com.app.service.notification.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationDAO notificationDao;

	@Override
	public List<Notification> findNotificationList() {
		List<Notification> notificationList = notificationDao.findNotificationList();
		return notificationList;
	}

	@Override
	public int saveNotification(Notification notification) {
		int result = notificationDao.saveNotification(notification);
		return result;
	}

	@Override
	public Notification findNotificationById(int id) {
		Notification notification = notificationDao.findNotificationById(id);
		return notification;
	}

	@Override
	public int removeNotification(int id) {
		int result = notificationDao.removeNotification(id);
		return result;
	}

	@Override
	public int modifyNotification(Notification notification) {
		int result = notificationDao.modifyNotification(notification);
		return result;
	}
}