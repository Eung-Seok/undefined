package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.project.Project;
import com.app.dto.projectMember.ProjectMember;
import com.app.service.project.ProjectService;
import com.app.service.projectMember.ProjectMemberService;

@Controller
public class PageController {
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectMemberService projectMemberService;

    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() { return "dashboard"; }

    @GetMapping("/projects")
	public String projectList(Model model) {
		
		List<ProjectMember> projectMemberList = projectMemberService.findProjectMemberList(); 
		List<Integer> projectIdList = new ArrayList<Integer>();
		
		for(ProjectMember p: projectMemberList) {
			if(String.valueOf(p.getUserId()).equals("1001")) {
				projectIdList.add(Integer.parseInt(String.valueOf(p.getProjectId())));
			}
		}
		
		List<Project> projectList = new ArrayList<Project>();
		for(int i : projectIdList) {
			projectList.add(projectService.findProjectById(i));
		}
		
	    model.addAttribute("projectList", projectList);
	    return "projects";
	}
    
    @GetMapping("/calendar")
    public String calendar() { return "calendar"; }

    @GetMapping("/board")
    public String board() { return "board/board"; }

    @GetMapping("/board/write")
    public String boardWrite() { return "board/board-write"; }

    @GetMapping("/board/view")
    public String boardView() { return "board/board-view"; }

    @GetMapping("/employees")
    public String employees() { return "employee/employees"; }

    @GetMapping("/employees/view")
    public String employeeView() { return "employee/employee-view"; }

    @GetMapping("/mypage")
    public String mypage() { return "mypage"; }

    @GetMapping("/search")
    public String search() { return "search"; }
}
