package com.app.service.taskAssignee;

import java.util.List;

import com.app.dto.taskAssignee.TaskAssignee;

public interface TaskAssigneeService {
	List<TaskAssignee> findTaskAssigneeList();
	
	List<TaskAssignee> findTaskAssigneeListByUserId(int userId);

	int saveTaskAssignee(TaskAssignee taskAssignee);

	TaskAssignee findTaskAssigneeById(int id);

	int removeTaskAssignee(int id);

	int modifyTaskAssignee(TaskAssignee taskAssignee);
}
