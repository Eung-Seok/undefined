package com.app.dao.userRole.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.user.UserDAO;
import com.app.dao.userRole.UserRoleDAO;
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
	
}
