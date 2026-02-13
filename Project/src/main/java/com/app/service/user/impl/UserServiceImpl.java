package com.app.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.user.UserDAO;
import com.app.dto.user.User;
import com.app.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDao;

	@Override
	public List<User> findUserList() {
		List<User> userList = userDao.findUserList();
		return userList;
	}

	@Override
	public int saveUser(User user) {
		int result = userDao.saveUser(user);
		return result;
	}

	@Override
	public User findUserByEmpno(int empno) {
		User user = userDao.findUserByEmpno(empno);
		return user;
	}

	@Override
	public int removeUser(int id) {
		int result = userDao.removeUser(id);
		return result;
	}

	@Override
	public int modifyUser(User user) {
		int result = userDao.modifyUser(user);
		return result;
	}
}
