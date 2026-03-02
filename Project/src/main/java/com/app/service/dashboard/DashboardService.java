package com.app.service.dashboard;

import java.util.List;

import com.app.dto.dashboard.Dashboard;

public interface DashboardService {

    int getActiveProjectCount();

    int getTodayTaskCount();

    int getDelayedTaskCount();

    List<Dashboard> getDashboardProjects();
}
