package com.app.dao.department;

import java.util.List;

import com.app.dto.department.Department;

public interface DepartmentDAO {
	List<Department> findDepartmentList();
}
