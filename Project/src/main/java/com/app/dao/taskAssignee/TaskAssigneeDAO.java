package com.app.dao.taskAssignee;

import java.util.List;

import com.app.dto.taskAssignee.TaskAssignee;

public interface TaskAssigneeDAO {
	List<TaskAssignee> findTaskAssigneeList();
}
