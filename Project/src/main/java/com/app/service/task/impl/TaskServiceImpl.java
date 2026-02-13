package com.app.service.task.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.task.TaskDAO;
import com.app.dto.task.Task;
import com.app.service.task.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskDAO taskDao;

	@Override
	public List<Task> findTaskList() {
		List<Task> taskList = taskDao.findTaskList();
		return taskList;
	}
}
