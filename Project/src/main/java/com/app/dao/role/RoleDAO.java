package com.app.dao.role;

import java.util.List;

import com.app.dto.role.Role;

public interface RoleDAO {
	List<Role> findRoleList();
	
	int saveRole(Role role);

	Role findRoleById(int id);

	int removeRole(int id);

	int modifyRole(Role role);
}
