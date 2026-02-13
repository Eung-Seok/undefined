package com.app.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.user.UserDAO;
import com.app.dto.user.User;
import com.app.service.user.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDao;

	@Override
	public List<User> findUserList() {
		List<User> userList = userDao.findUserList();
		return userList;
	}
}
