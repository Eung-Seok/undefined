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

	@Override
	public int saveTaskAssignee(TaskAssignee taskAssignee) {
		int result = taskAssigneeDao.saveTaskAssignee(taskAssignee);
		return result;
	}

	@Override
	public TaskAssignee findTaskAssigneeById(int id) {
		TaskAssignee taskAssignee = taskAssigneeDao.findTaskAssigneeById(id);
		return taskAssignee;
	}

	@Override
	public int removeTaskAssignee(int id) {
		int result = taskAssigneeDao.removeTaskAssignee(id);
		return result;
	}

	@Override
	public int modifyTaskAssignee(TaskAssignee taskAssignee) {
		int result = taskAssigneeDao.modifyTaskAssignee(taskAssignee);
		return result;
	}

	@Override
	public List<TaskAssignee> findTaskAssigneeListByUserId(int userId) {
		List<TaskAssignee> taskAgAssignees = taskAssigneeDao.findTaskAssigneeListByUserId(userId);
		return taskAgAssignees;
	}

	@Override
	public int removeTaskAssigneeByTaskId(int taskId) {
		int result = taskAssigneeDao.removeTaskAssigneeByTaskId(taskId);
		return result;
	}

	@Override
	public List<TaskAssignee> findTaskAssigneeListByTaskId(int taskId) {
		List<TaskAssignee> taskAssignees = taskAssigneeDao.findTaskAssigneeListByTaskId(taskId);
		return taskAssignees;
	}

	@Override
	public int removeTaskAssigneeByTaskIdAndUserId(int taskId, int userId) {
		int result = taskAssigneeDao.removeTaskAssigneeByTaskIdAndUserId(taskId, userId);
		return result;
	}
}
