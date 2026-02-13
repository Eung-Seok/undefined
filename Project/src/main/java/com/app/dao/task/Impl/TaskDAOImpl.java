package com.app.dao.task.Impl;

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

}
