package com.app.service.userRole.impl;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.userRole.UserRoleDAO;
import com.app.dto.userRole.UserRole;
import com.app.service.userRole.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	UserRoleDAO userRoleDao;

	@Override
	public List<UserRole> findUserRoleList() {
		List<UserRole> userRoleList = userRoleDao.findUserRoleList();
		return userRoleList;
	}

	@Override
	public int saveUserRole(UserRole userRole) {
		int result = userRoleDao.saveUserRole(userRole);
		return result;
	}

	@Override
	public UserRole findUserRoleById(int id) {
		UserRole userRole = userRoleDao.findUserRoleById(id);
		return userRole;
	}

	@Override
	public int removeUserRole(int id) {
		int result = userRoleDao.removeUserRole(id);
		return result;
	}

	@Override
	public int modifyUserRole(UserRole userRole) {
		int result = userRoleDao.modifyUserRole(userRole);
		return result;
	}
}
