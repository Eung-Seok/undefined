package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.department.DepartmentTreeNode;
import com.app.dto.project.Project;
import com.app.dto.projectMember.ProjectMember;
import com.app.dto.user.User;
import com.app.service.department.DepartmentService;
import com.app.service.project.ProjectService;
import com.app.service.projectMember.ProjectMemberService;
import com.app.service.user.UserService;

@Controller
public class PageController {
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectMemberService projectMemberService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DepartmentService departmentService;

	@GetMapping("/")
	public String root(HttpSession session) {

		if (session.getAttribute("loginUser") != null) {
			return "redirect:/dashboard";
		}
		return "redirect:/login";
	}

    @GetMapping("/projects")
	public String projectList(Model model, HttpSession session) {

    	User user = (User) session.getAttribute("loginUser");
    	Map<Integer, String> userNameMap = new HashMap<>();
    	
    	
		List<ProjectMember> projectMemberList = projectMemberService.findProjectMemberList(); 
		List<User> userList = userService.findUserList();
		List<Integer> projectIdList = new ArrayList<Integer>();
		List<Project> allProjectList = projectService.findProjectList();
		
		for(ProjectMember p: projectMemberList) {
			if(String.valueOf(p.getUserId()).equals(Integer.toString(user.getEmpno()))) {
				projectIdList.add(Integer.parseInt(String.valueOf(p.getProjectId())));
			}
		}
		
		for(User u: userList) {
			userNameMap.put(u.getEmpno(), u.getName());
		}
		
		List<Project> projectList = new ArrayList<Project>();
		for(int i : projectIdList) {
			projectList.add(projectService.findProjectById(i));
		}
		
		model.addAttribute("allProjectList", allProjectList);
	    model.addAttribute("projectList", projectList);
	    model.addAttribute("userNameMap", userNameMap);
	    return "projects";
	}

	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}

	@GetMapping("/employees")
	public String employees() {
		return "employee/employees";
	}
	
	@ResponseBody
	@GetMapping("/employees/tree") public  List<DepartmentTreeNode> tree(
            @RequestParam(defaultValue = "true") boolean includeUsers
    ) {
        return departmentService.findDepartmentTree(includeUsers);
    }
 

	@GetMapping("/employees/view")
	public String employeeView() {
		return "employee/employee-view";
	}

	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}

	@GetMapping("/search")
	public String search() {
		return "search";
	}
}
