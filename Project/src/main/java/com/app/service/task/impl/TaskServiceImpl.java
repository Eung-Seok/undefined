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

	@Override
	public int saveTask(Task task) {
		int result = taskDao.saveTask(task);
		return result;
	}

	@Override
	public Task findTaskById(int id) {
		Task task = taskDao.findTaskById(id);
		return task;
	}

	@Override
	public int removeTask(int id) {
		int result = taskDao.removeTask(id);
		return result;
	}

	@Override
	public int modifyTask(Task task) {
		int result = taskDao.modifyTask(task);
		return result;
	}

	@Override
	public List<Task> findTaskListByProjectId(int porjectId) {
		List<Task> taskList = taskDao.findTaskListByProjectId(porjectId);
		return taskList;
	}
}
