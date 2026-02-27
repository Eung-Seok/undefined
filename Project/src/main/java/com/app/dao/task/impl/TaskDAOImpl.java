package com.app.dao.task.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Task task = sqlSessionTemplate.selectOne("task_mapper.findTaskById", id);
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
	public List<Task> findTodayTaskByUser(int empno) {
	    return sqlSessionTemplate.selectList("task_mapper.findTodayTaskByUser", empno);
	}
	@Override
	public int updateTaskStatus(int taskId, String status) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("taskId", taskId);
	    params.put("status", status);
	    return sqlSessionTemplate.update("task_mapper.updateStatus", params);
	}
	@Override
    public double calculateProjectProgress(int projectId) {
        return sqlSessionTemplate.selectOne("task_mapper.calculateProjectProgress", projectId);
    }
	 @Override
	    public void completeTask(int taskId) {
	        sqlSessionTemplate.update("task_mapper.completeTask", taskId);
	    }

	 @Override
	 public int updateProjectProgress(int projectId) {
	     return sqlSessionTemplate.update("task_mapper.updateProjectProgress", projectId);
	 }
	 


}
