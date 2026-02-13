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

	@Override
	public int saveRole(Role role) {
		int result = sqlSessionTemplate.insert("role_mapper.saveRole", role);
		return result;
	}

	@Override
	public Role findRoleById(int id) {
		Role role = sqlSessionTemplate.selectOne("role_mapper.findRoleById",id);
		return role;
	}

	@Override
	public int removeRole(int id) {
		int result = sqlSessionTemplate.delete("role_mapper.removeRole", id);
		return result;
	}

	@Override
	public int modifyRole(Role role) {
		int result = sqlSessionTemplate.update("role_mapper.modifyRole", role);
		return result;
	}
	
}

