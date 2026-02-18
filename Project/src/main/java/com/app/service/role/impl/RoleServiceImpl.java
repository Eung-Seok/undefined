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

	@Override
	public int saveRole(Role role) {
		int result = roleDao.saveRole(role);
		return result;
	}

	@Override
	public Role findRoleById(int id) {
		Role role = roleDao.findRoleById(id);
		return role;
	}

	@Override
	public int removeRole(int id) {
		int result = roleDao.removeRole(id);
		return result;
	}

	@Override
	public int modifyRole(Role role) {
		int result = roleDao.modifyRole(role);
		return result;
	}
}