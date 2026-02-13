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
	
}
