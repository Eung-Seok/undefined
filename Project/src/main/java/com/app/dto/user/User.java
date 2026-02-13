package com.app.dto.user;

import lombok.Data;

@Data
public class User {
	int empno;
	String email;
	String password;
	String name;
	String position;
	int departmentId;
	String status;
	String startedAt;
	String createdAt;
	String updatedAt;
}
