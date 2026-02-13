package com.app.dao.department.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.department.DepartmentDAO;
import com.app.dto.department.Department;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Department> findDepartmentList() {
		List<Department> departmentList = sqlSessionTemplate.selectList("department_mapper.findDepartmentList");
		return departmentList;
	}

	@Override
	public int saveDepartment(Department department) {
		int result = sqlSessionTemplate.insert("department_mapper.saveDepartment", department);
		return result;
	}

	@Override
	public Department findDepartmentById(int id) {
		Department department = sqlSessionTemplate.selectOne("department_mapper.findDepartmentById", id);
		return department;
	}

	@Override
	public int removeDepartment(int id) {
		int result = sqlSessionTemplate.delete("department_mapper.removeDepartment", id);
		return result;
	}

	@Override
	public int modifyDepartment(Department department) {
		int result = sqlSessionTemplate.update("department_mapper.modifyDepartment", department);
		return result;
	}
	
}
