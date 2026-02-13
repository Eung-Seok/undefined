package com.app.service.role;

import java.util.List;

import com.app.dto.role.Role;

public interface RoleService {
	List<Role> findRoleList();

	int saveRole(Role role);

	Role findRoleById(int id);

	int removeRole(int id);

	int modifyRole(Role role);
}
