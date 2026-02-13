package com.app.service.taskAssignee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.taskAssignee.TaskAssigneeDAO;
import com.app.dto.taskAssignee.TaskAssignee;
import com.app.service.taskAssignee.TaskAssigneeService;

@Service
public class TaskAssigneeServiceImpl implements TaskAssigneeService{

	@Autowired
	TaskAssigneeDAO taskAssigneeDao;

	@Override
	public List<TaskAssignee> findTaskAssigneeList() {
		List<TaskAssignee> taskAssigneeList = taskAssigneeDao.findTaskAssigneeList();
		return taskAssigneeList;
	}
}
