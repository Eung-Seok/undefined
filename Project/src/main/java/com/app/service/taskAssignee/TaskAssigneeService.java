package com.app.service.taskAssignee;

import java.util.List;

import com.app.dto.taskAssignee.TaskAssignee;

public interface TaskAssigneeService {
	List<TaskAssignee> findTaskAssigneeList();
	
	List<TaskAssignee> findTaskAssigneeListByUserId(int userId);
	
	List<TaskAssignee> findTaskAssigneeListByTaskId(int taskId);

	int saveTaskAssignee(TaskAssignee taskAssignee);

	TaskAssignee findTaskAssigneeById(int id);

	int removeTaskAssignee(int id);

	int removeTaskAssigneeByTaskId(int taskId);
	
	int removeTaskAssigneeByTaskIdAndUserId(int taskId, int userId);
	
	int modifyTaskAssignee(TaskAssignee taskAssignee);
	
}
