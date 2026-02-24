package com.app.dto.user;

import lombok.Data;

@Data
public class AdminUserUpdate {
	int empno;
	String email;
	String name;
	String position;
	int deptno;
	String status;
	String role;
}
