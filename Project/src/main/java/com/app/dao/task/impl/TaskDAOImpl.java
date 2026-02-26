package com.app.dao.task.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.task.TaskDAO;
import com.app.dto.task.Task;

@Repository
public class TaskDAOImpl implements TaskDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Task> findTaskList() {
		List<Task> taskList = sqlSessionTemplate.selectList("task_mapper.findTaskList");
		return taskList;
	}

	@Override
	public int saveTask(Task task) {
		int result = sqlSessionTemplate.insert("task_mapper.saveTask", task);
		return result;
	}

	@Override
	public Task findTaskById(int id) {
		Task task = sqlSessionTemplate.selectOne("task_mapper.findTaskById",id);
		return task;
	}

	@Override
	public int removeTask(int id) {
		int result = sqlSessionTemplate.delete("task_mapper.removeTask", id);
		return result;
	}

	@Override
	public int modifyTask(Task task) {
		int result = sqlSessionTemplate.update("task_mapper.modifyTask", task);
		return result;
	}

	@Override
	public List<Task> findTaskListByProjectId(int porjectId) {
		List<Task> taskList = sqlSessionTemplate.selectList("task_mapper.findTaskListByProjectId", porjectId);
		return taskList;
	}

}
