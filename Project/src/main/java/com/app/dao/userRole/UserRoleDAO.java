package com.app.dao.userRole;

import java.util.List;

import com.app.dto.userRole.UserRole;

public interface UserRoleDAO {
	List<UserRole> findUserRoleList();
	
	int saveUserRole(UserRole userRole);

	UserRole findUserRoleById(int id);

	int removeUserRole(int id);

	int modifyUserRole(UserRole userRole);
}
