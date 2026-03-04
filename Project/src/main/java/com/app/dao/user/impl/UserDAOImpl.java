package com.app.dao.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.user.UserDAO;
import com.app.dto.user.AdminUserUpdate;
import com.app.dto.user.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<User> findUserList() {
		List<User> userList = sqlSessionTemplate.selectList("user_mapper.findUserList");
		return userList;
	}

	@Override
	public int saveUser(User user) {
		int result = sqlSessionTemplate.insert("user_mapper.saveUser", user);
		return result;
	}

	@Override
	public User findUserByEmpno(int empno) {
		User user = sqlSessionTemplate.selectOne("user_mapper.findUserByEmpno", empno);
		return user;
	}

	@Override
	public int removeUser(int id) {
		int result = sqlSessionTemplate.delete("user_mapper.removeUser", id);
		return result;
	}

	@Override
	public int modifyUser(User user) {
		int result = sqlSessionTemplate.update("user_mapper.modifyUser", user);
		return result;
	}

	@Override
	public List<User> findUsersByDeptnoList(List<Integer> deptnoList) {
	    return sqlSessionTemplate.selectList("user_mapper.findUsersByDeptnoList", deptnoList);
	}

	@Override
	public int updateUserAdmin(AdminUserUpdate adminUserUpdate) {
		return sqlSessionTemplate.update("user_mapper.adminUserUpdate", adminUserUpdate);
	}

	@Override
	public int createUserAdmin(AdminUserUpdate adminUserUpdate) {
		return sqlSessionTemplate.insert("user_mapper.createUser", adminUserUpdate);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = sqlSessionTemplate.selectOne("user_mapper.findUserByEmail",email);
		return user;
	}


	
}
