package com.app.dao.userRole;

import java.util.List;

import com.app.dto.user.AdminUserUpdate;
import com.app.dto.userRole.UserRole;

public interface UserRoleDAO {
	List<UserRole> findUserRoleList();
	
	int saveUserRole(UserRole userRole);

	UserRole findUserRoleById(int id);
	
	UserRole findUserRoleByUserId(int userId);

	int removeUserRole(int id);

	int modifyUserRole(UserRole userRole);
	
	int adminUserRoleUpdate(AdminUserUpdate adminUserUpdate);
}
