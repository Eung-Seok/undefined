package com.app.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.role.RoleDAO;
import com.app.dto.role.Role;
import com.app.service.role.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDAO roleDao;

	@Override
	public List<Role> findRoleList() {
		List<Role> roleList = roleDao.findRoleList();
		return roleList;
	}
}