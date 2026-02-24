package com.app.service.userRole;

import java.util.List;

import com.app.dto.user.User;
import com.app.dto.userRole.UserRole;

public interface UserRoleService {
	List<UserRole> findUserRoleList();

	int saveUserRole(UserRole userRole);

	UserRole findUserRoleById(int id);

	UserRole findUserRoleByUserId(int userId);
	
	int removeUserRole(int id);

	int modifyUserRole(UserRole userRole);
	
	
}
