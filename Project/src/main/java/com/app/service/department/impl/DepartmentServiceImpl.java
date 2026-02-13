package com.app.service.department.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.department.DepartmentDAO;
import com.app.dto.department.Department;
import com.app.service.department.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentDAO departmentDao;

	@Override
	public List<Department> findDepartmentList() {
		List<Department> departmentList = departmentDao.findDepartmentList();
		return departmentList;
	}
}
