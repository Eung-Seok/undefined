package com.app.service.mypage.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.task.Task;
import com.app.service.mypage.MyPageService;
import com.app.service.task.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final TaskService taskService;

    @Override
    public List<Task> getMyTodayTask(int empno) {
        return taskService.getTodayTask(empno);
    }
}
