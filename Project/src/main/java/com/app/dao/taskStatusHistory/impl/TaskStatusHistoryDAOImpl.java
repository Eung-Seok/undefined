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
	
}

