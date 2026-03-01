package com.app.service.dashboard.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.app.dto.dashboard.Dashboard;
import com.app.service.dashboard.DashboardService;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final SqlSession sqlSession;

    public int getActiveProjectCount() {
        return sqlSession.selectOne("com.app.mapper.dashboard.DashboardMapper.countActiveProjects");
    }

    public int getTodayTaskCount() {
        return sqlSession.selectOne("com.app.mapper.dashboard.DashboardMapper.countTodayTasks");
    }

    public int getDelayedTaskCount() {
        return sqlSession.selectOne("com.app.mapper.dashboard.DashboardMapper.countDelayedTasks");
    }

    public List<Dashboard> getDashboardProjects() {
        return sqlSession.selectList("com.app.mapper.dashboard.DashboardMapper.findDashboardProjects");
    }
}