package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dto.project.Project;
import com.app.dto.projectMember.ProjectMember;
import com.app.dto.report.Report;
import com.app.dto.user.User;
import com.app.service.board.BoardService;
import com.app.service.comment.CommentService;
import com.app.service.notification.NotificationService;
import com.app.service.project.ProjectService;
import com.app.service.projectMember.ProjectMemberService;
import com.app.service.report.ReportService;
import com.app.service.user.UserService;

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
	@Autowired
	ReportService reportService;
	@Autowired
	UserService userService;

	@PostMapping("/report/save")
	public String saveReport(@RequestParam("taskContent") String taskContent,
			@RequestParam("issueContent") String issueContent, @RequestParam("projectId") int projectId) {

		System.out.println(taskContent);
		System.out.println(issueContent);
		System.out.println("저장실행");

		return "redirect:/project/overview?projectId=" + projectId;
	}

	@PostMapping("/save")
	public String saveProject(Project project, @RequestParam("pmUserId") int pmUserId) {

		project.setOwnerUserId(pmUserId);
		projectService.saveProject(project);

		ProjectMember projectMember = new ProjectMember();

		int projectId = project.getId();

		projectMember.setProjectId(projectId);
		projectMember.setProjectRole("PM");
		projectMember.setUserId(pmUserId);
		projectMember.setStatus("ONGOING");

		projectMemberService.saveProjectMember(projectMember);

		return "redirect:/project/overview?projectId=" + projectId;
	}

	@GetMapping("/report")
	public String report(@RequestParam("projectId") int projectId, Model model) {
		Project project = projectService.findProjectById(projectId);
		model.addAttribute("project", project);

		return "project/report";
	}

	@PostMapping("/project/report/save")
	public String saveReport(Report dto, RedirectAttributes rttr) {

		reportService.saveReport(dto);

		rttr.addAttribute("success", "true");
		rttr.addAttribute("projectId", dto.getProjectId());

		return "redirect:/project/report";
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
		List<ProjectMember> projectMemberList = projectMemberService.findProjectMemberListByProjectId(projectId);
		List<Integer> userIdList = new ArrayList<Integer>();
		List<User> userList = new ArrayList<User>();
		Map<Integer, String> userRoleMap = new HashMap<Integer, String>();
		for (ProjectMember pm : projectMemberList) {
			userIdList.add(pm.getUserId());
			userRoleMap.put(pm.getUserId(), pm.getProjectRole());
		}

		for (Integer i : userIdList) {
			userList.add(userService.findUserByEmpno(i));
		}
		model.addAttribute("project", project);
		model.addAttribute("userList", userList);
		model.addAttribute("userRoleMap", userRoleMap);

		return "project/members";
	}

	@GetMapping("/members/add")
	public String addMembers(Model model, @RequestParam("projectId") int projectId) {
		Project project = projectService.findProjectById(projectId);
		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);
		model.addAttribute("project", project);

		return "project/inviteMember";
	}

	@PostMapping("/members/add")
	public String addProjectMembersBulk(
	        @RequestParam("projectId") int projectId,
	        @RequestParam(value = "selectedEmpnos", required = false) List<Integer> selectedEmpnos,
	        @RequestParam(value = "roleByEmpno", required = false) Map<Integer, String> roleByEmpno
	) {
	    projectMemberService.addMembersBulkPerUserRole(projectId, selectedEmpnos, roleByEmpno);
		return "redirect:/project/members?projectId=" + projectId;
	}
	
	@PostMapping("/members/delete")
	public String deleteProjectMember(@RequestParam("projectId") Long projectId,
	        @RequestParam("empno") Long empno) {
		
		projectMemberService.removeProjectMemberByProjectIdAndUserId(projectId, empno);
		
		return "redirect:/project/members?projectId=" + projectId;
	}
	
	@GetMapping("members/edit")
    public String editForm(@RequestParam("projectId") int projectId,
                           @RequestParam("empno") int empno,
                           HttpSession session,
                           Model model) {

        // (권장) 권한 체크: ADMIN 또는 PM
        String role = (String) session.getAttribute("loginUserRole");
        if (!( "ADMIN".equals(role) || "PM".equals(role) )) {
            return "redirect:/project/members?projectId=" + projectId + "&error=forbidden";
        }
        User user = userService.findUserByEmpno(empno);
        
        
        model.addAttribute("projectId", projectId);
        model.addAttribute("empno", empno);
        model.addAttribute("name", user.getName());

        return "project/members_edit"; // /WEB-INF/views/project/members_edit.jsp
    }
	
	@PostMapping("members/edit")
    public String editSubmit(@RequestParam("projectId") int projectId,
                             @RequestParam("empno") int empno,
                             @RequestParam("projectRole") String projectRole,
                             HttpSession session) {

        String role = (String) session.getAttribute("loginUserRole");
        if (!( "ADMIN".equals(role) || "PM".equals(role) )) {
            return "redirect:/project/members?projectId=" + projectId + "&error=forbidden";
        }

        projectMemberService.updateMemberRole(projectId, empno, projectRole);

        return "redirect:/project/members?projectId=" + projectId;
    }

	@GetMapping("/settings")
	public String settings(@RequestParam("projectId") int projectId, Model model) {
		Project project = projectService.findProjectById(projectId);
		model.addAttribute("project", project);
		return "project/settings";
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {

		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);

		return "project/create";
	}

}
