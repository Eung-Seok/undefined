package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

import com.app.dto.dashboard.Dashboard;
import com.app.service.dashboard.DashboardService;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("activeProjectCount",
                dashboardService.getActiveProjectCount());
        model.addAttribute("todayTaskCount",
                dashboardService.getTodayTaskCount());
        model.addAttribute("delayedTaskCount",
                dashboardService.getDelayedTaskCount());
        model.addAttribute("projectList",
                dashboardService.getDashboardProjects());
        return "dashboard";
    }
}