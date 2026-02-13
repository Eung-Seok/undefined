package com.app.dao.user;

import java.util.List;

import com.app.dto.user.User;

public interface UserDAO {
	List<User> findUserList();
	
	int saveUser(User user);

	User findUserByEmpno(int empno);

	int removeUser(int id);

	int modifyUser(User user);
}
