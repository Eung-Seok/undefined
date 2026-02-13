package com.app.dao.department;

import java.util.List;

import com.app.dto.department.Department;

public interface DepartmentDAO {
	List<Department> findDepartmentList();
	
	int saveDepartment(Department department);

	Department findDepartmentById(int id);

	int removeDepartment(int id);

	int modifyDepartment(Department department);
}
