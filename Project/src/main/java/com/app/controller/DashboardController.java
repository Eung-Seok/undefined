package com.app.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

import com.app.dto.dashboard.Dashboard;
import com.app.dto.project.Project;
import com.app.dto.projectMember.ProjectMember;
import com.app.dto.task.Task;
import com.app.dto.taskAssignee.TaskAssignee;
import com.app.dto.user.User;
import com.app.service.dashboard.DashboardService;
import com.app.service.project.ProjectService;
import com.app.service.projectMember.ProjectMemberService;
import com.app.service.task.TaskService;
import com.app.service.taskAssignee.TaskAssigneeService;
import com.app.service.user.UserService;

@Controller
@RequiredArgsConstructor
public class DashboardController {
	
	@Autowired
	UserService userService;
	@Autowired
	ProjectService projectService;
	@Autowired
	ProjectMemberService projectMemberService;
	@Autowired
	TaskService taskService;
	@Autowired
	TaskAssigneeService taskAssigneeService;
    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
   
    	List<Task> taskList = taskService.findTaskList();
    	List<ProjectMember> pmList = projectMemberService.findProjectMemberList();
    	User user = (User) session.getAttribute("loginUser");
    	
    	int userId = user.getEmpno();
    	Integer count3 = 0;
    	Integer count4 = 0; 
    	Date now = new Date(System.currentTimeMillis());
    	
    	List<Project> userProjectList = new ArrayList<Project>();
    	List<Task> userTaskList = new ArrayList<Task>();
    	
    	Map<Integer, String> userName = new HashMap<Integer, String>();
    	Map<Integer, Integer> projectMemberCount = new HashMap<Integer, Integer>();
    	Map<Integer, Integer> projectTaskCount = new HashMap<Integer, Integer>();
    	
    	for(ProjectMember pm: pmList) {
    		if(pm.getUserId() == userId) {
    			userProjectList.add(projectService.findProjectById(pm.getProjectId()));
    		}
    	}
    	
    	for(User u: userService.findUserList()) {
    		userName.put(u.getEmpno(), u.getName());
    	}
    	
    	for(Project p: projectService.findProjectList()) {
    		Integer count = 0;
    		Integer count2 = 0;
    		for(ProjectMember pm: projectMemberService.findProjectMemberListByProjectId(p.getId())) {
    			count++;
    			if(pm.getUserId() == userId) {
    				count3++;
    			}
    		}
    		for(Task t: taskService.findTaskListByProjectId(p.getId())) {
    			for(TaskAssignee ta: taskAssigneeService.findTaskAssigneeListByTaskId(t.getId())) {
    				if(ta.getUserId() == userId) {
    					userTaskList.add(t);
    					if (!now.before(t.getStartDate()) && !now.after(t.getDueDate())) {
    						count2++;
    					}
    				}
    			}
    		}
    		projectMemberCount.put(p.getId(), count);
    		projectTaskCount.put(p.getId(), count2);
    		count4 += count2;
    	}
    	
        model.addAttribute("activeProjectCount",
                dashboardService.getActiveProjectCount());
        model.addAttribute("todayTaskCount",
                dashboardService.getTodayTaskCount());
        model.addAttribute("delayedTaskCount",
                dashboardService.getDelayedTaskCount());
        model.addAttribute("projectList", projectService.findProjectList());
        model.addAttribute("userProjectList",userProjectList);
        model.addAttribute("userName",userName);
        model.addAttribute("projectMemberCount",projectMemberCount);
        model.addAttribute("projectTaskCount",projectTaskCount);
        model.addAttribute("taskList", taskList);
        model.addAttribute("userTaskList", userTaskList);
        model.addAttribute("count3", count3);
        model.addAttribute("count4", count4);
        return "dashboard";
    }
}