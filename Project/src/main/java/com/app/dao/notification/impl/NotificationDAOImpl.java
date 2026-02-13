package com.app.dao.notification.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.notification.NotificationDAO;
import com.app.dto.notification.Notification;


@Repository
public class NotificationDAOImpl implements NotificationDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Notification> findNotificationList() {
		List<Notification> notificationList = sqlSessionTemplate.selectList("notification_mapper.findNotificationList");
		return notificationList;
	}
	
}