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

	@Override
	public int saveNotification(Notification notification) {
		int result = sqlSessionTemplate.insert("notification_mapper.saveNotification", notification);
		return result;
	}

	@Override
	public Notification findNotificationById(int id) {
		Notification notification = sqlSessionTemplate.selectOne("notification_mapper.findNotificationById", id);
		return notification;
	}

	@Override
	public int removeNotification(int id) {
		int result = sqlSessionTemplate.delete("notification_mapper.removeNotification", id);
		return result;
	}

	@Override
	public int modifyNotification(Notification notification) {
		int result = sqlSessionTemplate.update("notification_mapper.modifyNotification", notification);
		return result;
	}
	
}