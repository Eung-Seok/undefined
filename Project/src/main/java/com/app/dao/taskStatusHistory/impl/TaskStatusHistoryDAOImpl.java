package com.app.dao.taskStatusHistory.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.taskStatusHistory.TaskStatusHistoryDAO;
import com.app.dto.taskStatusHistory.TaskStatusHistory;

@Repository
public class TaskStatusHistoryDAOImpl implements TaskStatusHistoryDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<TaskStatusHistory> findTaskStatusHistoryList() {
		List<TaskStatusHistory> taskStatusHistoryList = sqlSessionTemplate.selectList("taskStatusHistory_mapper.findTaskStatusHistoryList");
		return taskStatusHistoryList;
	}

	@Override
	public int saveTaskStatusHistory(TaskStatusHistory taskStatusHistory) {
		int result = sqlSessionTemplate.insert("taskStatusHistory_mapper.saveTaskStatusHistory", taskStatusHistory);
		return result;
	}

	@Override
	public TaskStatusHistory findTaskStatusHistoryById(int id) {
		TaskStatusHistory taskStatusHistory = sqlSessionTemplate.selectOne("taskStatusHistory_mapper.findTaskStatusHistoryById",id);
		return taskStatusHistory;
	}

	@Override
	public int removeTaskStatusHistory(int id) {
		int result = sqlSessionTemplate.delete("taskStatusHistory_mapper.removeTaskStatusHistory", id);
		return result;
	}

	@Override
	public int modifyTaskStatusHistory(TaskStatusHistory taskStatusHistory) {
		int result = sqlSessionTemplate.update("taskStatusHistory_mapper.modifyTaskStatusHistory", taskStatusHistory);
		return result
				;
	}
	
}

