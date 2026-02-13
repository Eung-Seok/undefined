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
}
