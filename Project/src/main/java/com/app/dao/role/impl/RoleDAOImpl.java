package com.app.dao.role.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.role.RoleDAO;
import com.app.dto.role.Role;


@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Role> findRoleList() {
		List<Role> roleList = sqlSessionTemplate.selectList("role_mapper.findRoleList");
		return roleList;
	}
	
}

