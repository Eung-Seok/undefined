package com.app.dao.taskAssignee.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.taskAssignee.TaskAssigneeDAO;
import com.app.dto.taskAssignee.TaskAssignee;

@Repository
public class TaskAssigneeDAOImpl implements TaskAssigneeDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<TaskAssignee> findTaskAssigneeList() {
		List<TaskAssignee> taskAssigneeList = sqlSessionTemplate.selectList("taskAssignee_mapper.findTaskAssigneeList");
		return taskAssigneeList;
	}
	
}

