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
}