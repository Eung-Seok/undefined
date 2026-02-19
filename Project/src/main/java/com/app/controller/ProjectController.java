package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.app.service.projectMember.ProjectMemberService;
import com.app.service.projectMember.impl.ProjectMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.project.Project;
import com.app.dto.projectMember.ProjectMember;
import com.app.service.board.BoardService;
import com.app.service.board.impl.BoardServiceImpl;
import com.app.service.comment.CommentService;
import com.app.service.comment.impl.CommentServiceImpl;
import com.app.service.notification.NotificationService;
import com.app.service.notification.impl.NotificationServiceImpl;
import com.app.service.project.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	NotificationService notificationService;
	@Autowired
	CommentService commentService;
	@Autowired
	BoardService boardService;
	@Autowired
	ProjectMemberService projectMemberService;
	@Autowired
	ProjectService projectService;
	
	@PostMapping("/report/save")
	public String saveReport(
	        @RequestParam("taskContent") String taskContent,
	        @RequestParam("issueContent") String issueContent,
	        @RequestParam("projectId") int projectId) {  

	    System.out.println(taskContent);
	    System.out.println(issueContent);
	    System.out.println("저장실행");

	    return "redirect:/project/overview?projectId=" + projectId;
	}




	@GetMapping("/report")
	public String report(@RequestParam("projectId") int projectId, Model model) {
		Project project = projectService.findProjectById(projectId);
		model.addAttribute("project", project);

		return "project/report";
	}
	
	@GetMapping("/overview")
	public String overview(@RequestParam("projectId") int projectId, Model model) {
		Project project = projectService.findProjectById(projectId);
		model.addAttribute("project", project);

		return "project/overview";
	}
	


	@GetMapping("/tasks")
	public String tasks(@RequestParam("projectId") int projectId, Model model) {
	    Project project = projectService.findProjectById(projectId);
	    model.addAttribute("project", project);

	    return "project/tasks";
	}

	@GetMapping("/calendar")
	public String calendar(@RequestParam("projectId") int projectId, Model model) {
	    Project project = projectService.findProjectById(projectId);
	    model.addAttribute("project", project);
	    return "project/calendar";
	}

	@GetMapping("/wbs")
	public String wbs(@RequestParam("projectId") int projectId, Model model) {
	    Project project = projectService.findProjectById(projectId);
	    model.addAttribute("project", project);
	    return "project/wbs";
	}

	@GetMapping("/issues")
	public String issues(@RequestParam("projectId") int projectId, Model model) {
	    Project project = projectService.findProjectById(projectId);
	    model.addAttribute("project", project);

	    return "project/issues";
	}

	@GetMapping("/docs")
	public String docs(@RequestParam("projectId") int projectId, Model model) {
	    Project project = projectService.findProjectById(projectId);
	    model.addAttribute("project", project);
	    return "project/docs";
	}

	@GetMapping("/members")
	public String members(@RequestParam("projectId") int projectId, Model model) {
	    Project project = projectService.findProjectById(projectId);
	    model.addAttribute("project", project);

	    return "project/members";
	}

	@GetMapping("/settings")
	public String settings(@RequestParam("projectId") int projectId, Model model) {
	    Project project = projectService.findProjectById(projectId);
	    model.addAttribute("project", project);
	    return "project/settings";
	}

}
