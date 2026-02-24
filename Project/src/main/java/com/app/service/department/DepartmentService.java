package com.app.service.department;

import java.util.List;

import com.app.dto.department.Department;
import com.app.dto.department.DepartmentTreeNode;

public interface DepartmentService {
	List<Department> findDepartmentList();

	int saveDepartment(Department department);

	Department findDepartmentById(int id);
	
	List<DepartmentTreeNode> findDepartmentTree(boolean includeUsers);

	int removeDepartment(int id);

	int modifyDepartment(Department department);
}
