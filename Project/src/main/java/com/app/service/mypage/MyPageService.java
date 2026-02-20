package com.app.service.mypage;

import java.util.List;

import com.app.dto.task.Task;

public interface MyPageService {
	List<Task> getMyTodayTask(int empno);
}
