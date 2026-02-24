package com.app.service.user;

import java.util.List;

import com.app.dto.user.AdminUserUpdate;
import com.app.dto.user.User;

public interface UserService {
	List<User> findUserList();

	int saveUser(User user);

	User findUserByEmpno(int empno);

	int removeUser(int id);

	int modifyUser(User user);
	
	int updateUserAdmin(AdminUserUpdate adminUserUpdate);
}
