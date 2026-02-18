package com.app.dto.department;

import lombok.Data;

@Data
public class Department {
	int id;
	int deptno;
	String name;
	Integer parentDepartmentId;
	String createdAt;
	String startedAt;
	String updatedAt;
}
