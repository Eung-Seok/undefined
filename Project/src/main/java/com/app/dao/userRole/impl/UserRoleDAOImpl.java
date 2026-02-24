package com.app.dao.userRole.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.user.UserDAO;
import com.app.dao.userRole.UserRoleDAO;
import com.app.dto.user.AdminUserUpdate;
import com.app.dto.userRole.UserRole;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<UserRole> findUserRoleList() {
		List<UserRole> userRoleList = sqlSessionTemplate.selectList("userRole_mapper.findUserRoleList");
		return userRoleList;
	}

	@Override
	public int saveUserRole(UserRole userRole) {
		int result = sqlSessionTemplate.insert("userRole_mapper.saveUserRole", userRole);
		return result;
	}

	@Override
	public UserRole findUserRoleById(int id) {
		UserRole userRole = sqlSessionTemplate.selectOne("userRole_mapper.findUserRoleById",id);
		return userRole;
	}

	@Override
	public int removeUserRole(int id) {
		int result = sqlSessionTemplate.delete("userRole_mapper.removeUserRole", id);
		return result;
	}

	@Override
	public int modifyUserRole(UserRole userRole) {
		int result = sqlSessionTemplate.update("userRole_mapper.modifyUserRole", userRole);
		return result;
	}

	@Override
	public UserRole findUserRoleByUserId(int userId) {
		UserRole userRole = sqlSessionTemplate.selectOne("userRole_mapper.findUserRoleByUserId", userId);
		return userRole;
	}

	@Override
	public int adminUserRoleUpdate(AdminUserUpdate adminUserUpdate) {
		int result = sqlSessionTemplate.update("userRole_mapper.adminUserRoleUpdate", adminUserUpdate);
		return result;
	}

	@Override
	public int adminUserRoleCreate(AdminUserUpdate adminUserUpdate) {
		int result = sqlSessionTemplate.insert("userRole_mapper.createUserRole", adminUserUpdate);
		return result;
	}
	
}
