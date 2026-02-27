package com.app.dao.taskAssignee.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public int saveTaskAssignee(TaskAssignee taskAssignee) {
		int result = sqlSessionTemplate.insert("taskAssignee_mapper.saveTaskAssignee", taskAssignee);
		return result;
	}

	@Override
	public TaskAssignee findTaskAssigneeById(int id) {
		TaskAssignee taskAssignee = sqlSessionTemplate.selectOne("taskAssignee_mapper.findTaskAssigneeById",id);
		return taskAssignee;
	}

	@Override
	public int removeTaskAssignee(int id) {
		int result = sqlSessionTemplate.delete("taskAssignee_mapper.removeTaskAssignee", id);
		return result;
	}

	@Override
	public int modifyTaskAssignee(TaskAssignee taskAssignee) {
		int result = sqlSessionTemplate.update("taskAssignee_mapper.modifyTaskAssignee", taskAssignee);
		return result;
	}

	@Override
	public List<TaskAssignee> findTaskAssigneeListByUserId(int userId) {		
		List<TaskAssignee> taskAssignees = sqlSessionTemplate.selectList("taskAssignee_mapper.findTaskAssigneeListByUserId",userId);
		return taskAssignees;
	}

	@Override
	public int removeTaskAssigneeByTaskId(int taskId) {
		int result = sqlSessionTemplate.delete("taskAssignee_mapper.removeTaskAssigneeByTaskId", taskId);
		return result;
	}

	@Override
	public List<TaskAssignee> findTaskAssigneeListByTaskId(int taskId) {
		List<TaskAssignee> taskAssignees = sqlSessionTemplate.selectList("taskAssignee_mapper.findTaskAssigneeListByTaskId", taskId);
		return taskAssignees;
	}

	@Override
	public int removeTaskAssigneeByTaskIdAndUserId(int taskId, int userId) {
		Map<String, Object> param = new HashMap<>();
	    param.put("taskId", taskId);
	    param.put("userId", userId);
		int result = sqlSessionTemplate.delete("taskAssignee_mapper.removeTaskAssigneeByTaskIdAndUserId", param);
		return result;
	}
	
}

