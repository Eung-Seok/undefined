package com.app.service.taskStatusHistory.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.taskStatusHistory.TaskStatusHistoryDAO;
import com.app.dto.taskStatusHistory.TaskStatusHistory;
import com.app.service.taskStatusHistory.TaskStatusHistoryService;

@Service
public class TaskStatusHistoryServiceImpl implements TaskStatusHistoryService{

	@Autowired
	TaskStatusHistoryDAO taskStatusHistoryDao;

	@Override
	public List<TaskStatusHistory> findTaskStatusHistoryList() {
		List<TaskStatusHistory> taskStatusHistoryList = taskStatusHistoryDao.findTaskStatusHistoryList();
		return taskStatusHistoryList;
	}

	@Override
	public int saveTaskStatusHistory(TaskStatusHistory taskStatusHistory) {
		int result = taskStatusHistoryDao.saveTaskStatusHistory(taskStatusHistory);
		return result;
	}

	@Override
	public TaskStatusHistory findTaskStatusHistoryById(int id) {
		TaskStatusHistory taskStatusHistory = taskStatusHistoryDao.findTaskStatusHistoryById(id);
		return taskStatusHistory;
	}

	@Override
	public int removeTaskStatusHistory(int id) {
		int result = taskStatusHistoryDao.removeTaskStatusHistory(id);
		return result;
	}

	@Override
	public int modifyTaskStatusHistory(TaskStatusHistory taskStatusHistory) {
		int result = taskStatusHistoryDao.modifyTaskStatusHistory(taskStatusHistory);
		return result;
	}
}
