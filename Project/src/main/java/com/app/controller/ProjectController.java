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

import com.app.dto.department.Department;
import com.app.dto.project.Project;
import com.app.dto.projectMember.ProjectMember;
import com.app.dto.report.Report;
import com.app.dto.task.Task;
import com.app.dto.taskAssignee.TaskAssignee;
import com.app.dto.user.User;
import com.app.service.board.BoardService;
import com.app.service.comment.CommentService;
import com.app.service.department.DepartmentService;
import com.app.service.notification.NotificationService;
import com.app.service.project.ProjectService;
import com.app.service.projectMember.ProjectMemberService;
import com.app.service.report.ReportService;
import com.app.service.task.TaskService;
import com.app.service.taskAssignee.TaskAssigneeService;
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
	@Autowired
	TaskService taskService;
	@Autowired
	TaskAssigneeService taskAssigneeService;
	@Autowired
	DepartmentService departmentService;

	@GetMapping("/report")
	public String list(@RequestParam("projectId") int projectId, HttpSession session, Model model) {
		User user = (User) session.getAttribute("loginUser");
		Project project = projectService.findProjectById(projectId);
		List<Report> reportList = reportService.findReportByProjectId(projectId);
		List<Report> userReportList = new ArrayList<Report>();
		List<User> userList = userService.findUserList();
		Map<Integer, String> userName = new HashMap<Integer, String>();

		for (User u : userList) {
			userName.put(u.getEmpno(), u.getName());
		}
		for (Report r : reportList) {
			if (r.getAuthorUserId() == user.getEmpno()) {
				userReportList.add(r);
			}
		}

		model.addAttribute("userName", userName);
		model.addAttribute("project", project);
		model.addAttribute("reportList", reportList);
		model.addAttribute("userReportList", userReportList);

		return "project/report_list";
	}

	@GetMapping("/report/view")
	public String viewReport(@RequestParam("projectId") int projectId, @RequestParam("reportId") int reportId,
			Model model, HttpSession session) {
		Report report = reportService.findReportById(reportId);
		Project project = projectService.findProjectById(projectId);
		User user = (User) session.getAttribute("loginUser");
		List<User> userList = userService.findUserList();
		Map<Integer, String> userName = new HashMap<Integer, String>();

		for (User u : userList) {
			userName.put(u.getEmpno(), u.getName());
		}

		model.addAttribute("user", user);
		model.addAttribute("userName", userName);
		model.addAttribute("project", project);
		model.addAttribute("report", report);

		return "/project/report_view";
	}

	@PostMapping("/report/write")
	public String writeSubmit(@RequestParam("projectId") int projectId, Report report, HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		report.setAuthorUserId(((User) session.getAttribute("loginUser")).getEmpno());

		reportService.saveReport(report);

		return "redirect:/project/report?projectId=" + projectId;
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

	@GetMapping("/report/write")
	public String report(@RequestParam("projectId") int projectId, Model model, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		Project project = projectService.findProjectById(projectId);
		List<Task> taskList = taskService.findTaskListByProjectId(projectId);
		List<TaskAssignee> taskAssigneesList = taskAssigneeService.findTaskAssigneeListByUserId(user.getEmpno());
		List<Task> userTaskList = new ArrayList<Task>();
		List<User> userList = userService.findUserList();
		for (TaskAssignee t : taskAssigneesList) {
			if (projectId == taskService.findTaskById(t.getTaskId()).getProjectId()) {
				userTaskList.add(taskService.findTaskById(t.getTaskId()));
			}
		}
		Map<Integer, String> userName = new HashMap<>();

		for (User u : userList) {
			userName.put(u.getEmpno(), u.getName());
		}

		model.addAttribute("project", project);
		model.addAttribute("taskList", userTaskList);
		return "project/writereport";
	}

	@GetMapping("/overview")
	public String overview(@RequestParam("projectId") int projectId, Model model) {
		Project project = projectService.findProjectById(projectId);
		model.addAttribute("project", project);

		return "project/overview";
	}

	@GetMapping("/tasks")
	public String tasks(@RequestParam("projectId") int projectId, Model model, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		Project project = projectService.findProjectById(projectId);
		List<Task> taskList = taskService.findTaskListByProjectId(projectId);
		List<TaskAssignee> taskAssigneesList = taskAssigneeService.findTaskAssigneeListByUserId(user.getEmpno());
		List<Task> userTaskList = new ArrayList<Task>();
		List<User> userList = userService.findUserList();
		for (TaskAssignee t : taskAssigneesList) {
			if (projectId == taskService.findTaskById(t.getTaskId()).getProjectId()) {
				userTaskList.add(taskService.findTaskById(t.getTaskId()));
			}
		}
		Map<Integer, String> userName = new HashMap<>();

		for (User u : userList) {
			userName.put(u.getEmpno(), u.getName());
		}

		model.addAttribute("userName", userName);
		model.addAttribute("taskList", taskList);
		model.addAttribute("userTaskList", userTaskList);
		model.addAttribute("project", project);
		model.addAttribute("userTaskNum", userTaskList.size());
		model.addAttribute("taskNum", taskList.size());

		return "project/tasks";
	}

	@GetMapping("/tasks/add")
	public String addTask(@RequestParam("projectId") int projectId, Model model) {
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
		model.addAttribute("userList", userList);
		model.addAttribute("project", project);
		return "project/addtask";
	}

	@PostMapping("tasks/add")
	public String addTaskAction(@RequestParam("projectId") int projectId, Task task) {

		task.setProjectId(projectId);
		taskService.saveTask(task);

		TaskAssignee taskAssignee = new TaskAssignee();
		taskAssignee.setTaskId(task.getId());
		taskAssignee.setUserId(task.getOwnerUserId());
		taskAssignee.setStatus("ONGOING");

		taskAssigneeService.saveTaskAssignee(taskAssignee);
		return "redirect:/project/tasks?projectId=" + projectId;
	}

	@GetMapping("/tasks/view")
	public String view(@RequestParam("projectId") int projectId, @RequestParam("taskId") int taskId,
			HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		Project project = projectService.findProjectById(projectId);
		Task task = taskService.findTaskById(taskId);
		Map<Integer, String> userName = new HashMap<>();
		List<User> userList = userService.findUserList();
		List<User> ul = new ArrayList<User>();
		List<ProjectMember> pmList = projectMemberService.findProjectMemberListByProjectId(projectId);
		for(ProjectMember pm: pmList) {
			ul.add(userService.findUserByEmpno(pm.getUserId()));
		}
		for (User u : userList) {
			userName.put(u.getEmpno(), u.getName());
		}
		List<User> taskUserList = new ArrayList<User>();
		List<TaskAssignee> taskAssigneeList = taskAssigneeService.findTaskAssigneeListByTaskId(taskId);
		for(TaskAssignee ta: taskAssigneeList) {
			taskUserList.add(userService.findUserByEmpno(ta.getUserId()));
		}

		model.addAttribute("taskUserList", taskUserList);
		model.addAttribute("userList", ul);
		model.addAttribute("userName", userName);
		model.addAttribute("project", project);
		model.addAttribute("task", task);

		return "project/task_view";
	}

	@PostMapping("/tasks/delete")
	public String deleteTask(@RequestParam("taskId") int taskId, @RequestParam("projectId") int projectId) {

		taskAssigneeService.removeTaskAssigneeByTaskId(taskId);
		taskService.removeTask(taskId);

		return "redirect:/project/tasks?projectId=" + projectId;
	}
	
	@PostMapping("/tasks/assignees/add")
	public String addAssignee(@RequestParam("taskId") int taskId, @RequestParam("projectId") int projectId, @RequestParam("empno") int empno) {
		
		List<TaskAssignee> taList = taskAssigneeService.findTaskAssigneeListByTaskId(taskId);
		for(TaskAssignee ta: taList) {
			if(ta.getUserId() == empno) {
				return "redirect:/project/tasks/view?projectId=" + projectId + "&taskId=" + taskId;
			}
		}
		TaskAssignee ta = new TaskAssignee();
		ta.setTaskId(taskId);
		ta.setUserId(empno);
		ta.setStatus("ONGOING");
		taskAssigneeService.saveTaskAssignee(ta);
		
		
		return "redirect:/project/tasks/view?projectId=" + projectId + " &taskId=" + taskId;
	}

	@GetMapping("/tasks/edit")
	public String editTask(@RequestParam("projectId") int projectId, @RequestParam("taskId") int taskId, HttpSession session, Model model) {
		Task task = taskService.findTaskById(taskId);
		Project project = projectService.findProjectById(projectId);
		List<User> userList = new ArrayList<User>();
		List<TaskAssignee> taskAssigneeList = taskAssigneeService.findTaskAssigneeListByTaskId(taskId);
		for(TaskAssignee ta: taskAssigneeList) {
			userList.add(userService.findUserByEmpno(ta.getUserId()));
		}

		model.addAttribute("userList", userList);
		model.addAttribute("project", project);
		model.addAttribute("task", task);

		return "/project/task_edit";
	}
	
	@PostMapping("/tasks/edit")
	public String editTaskAction(Task task, @RequestParam("projectId") int projectId, @RequestParam("id") int taskId) {
		
		int result = taskService.modifyTask(task);
		TaskAssignee taskAssignee = new TaskAssignee();
		List<TaskAssignee> taList = taskAssigneeService.findTaskAssigneeListByTaskId(taskId);
		for(TaskAssignee ta: taList) {
			if(ta.getUserId() == task.getOwnerUserId()) {
				return "redirect:/project/tasks/view?projectId=" + projectId + "&taskId=" + taskId;
			}
		}
		taskAssignee.setTaskId(taskId);
		taskAssignee.setUserId(task.getOwnerUserId());
		taskAssignee.setStatus("ONGOING");
		taskAssigneeService.saveTaskAssignee(taskAssignee);
		
		return "redirect:/project/tasks/view?projectId=" + projectId + "&taskId=" + taskId;
	}
	
	@PostMapping("/tasks/assignees/delete")
	public String deleteAssignees(@RequestParam("projectId") int projectId, @RequestParam("taskId") int taskId, @RequestParam("empno") int empno) {
		
		taskAssigneeService.removeTaskAssigneeByTaskIdAndUserId(taskId, empno);
		
		return "redirect:/project/tasks/view?projectId=" + projectId + "&taskId=" + taskId;
	}

	@GetMapping("/calendar")
	public String calendar(@RequestParam("projectId") int projectId, Model model) {
		Project project = projectService.findProjectById(projectId);
		model.addAttribute("project", project);
		return "project/calendar";
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
	public String addProjectMembersBulk(@RequestParam("projectId") int projectId,
			@RequestParam(value = "selectedEmpnos", required = false) List<Integer> selectedEmpnos,
			@RequestParam(value = "roleByEmpno", required = false) Map<Integer, String> roleByEmpno) {
		projectMemberService.addMembersBulkPerUserRole(projectId, selectedEmpnos, roleByEmpno);
		return "redirect:/project/members?projectId=" + projectId;
	}

	@PostMapping("/members/delete")
	public String deleteProjectMember(@RequestParam("projectId") Long projectId, @RequestParam("empno") Long empno) {

		projectMemberService.removeProjectMemberByProjectIdAndUserId(projectId, empno);

		return "redirect:/project/members?projectId=" + projectId;
	}

	@GetMapping("members/edit")
	public String editForm(@RequestParam("projectId") int projectId, @RequestParam("empno") int empno,
			HttpSession session, Model model) {

		// (권장) 권한 체크: ADMIN 또는 PM
		String role = (String) session.getAttribute("loginUserRole");
		if (!("ADMIN".equals(role) || "PM".equals(role))) {
			return "redirect:/project/members?projectId=" + projectId + "&error=forbidden";
		}
		User user = userService.findUserByEmpno(empno);

		model.addAttribute("projectId", projectId);
		model.addAttribute("empno", empno);
		model.addAttribute("name", user.getName());

		return "project/members_edit"; // /WEB-INF/views/project/members_edit.jsp
	}

	@PostMapping("members/edit")
	public String editSubmit(@RequestParam("projectId") int projectId, @RequestParam("empno") int empno,
			@RequestParam("projectRole") String projectRole, HttpSession session) {

		String role = (String) session.getAttribute("loginUserRole");
		if (!("ADMIN".equals(role) || "PM".equals(role))) {
			return "redirect:/project/members?projectId=" + projectId + "&error=forbidden";
		}

		projectMemberService.updateMemberRole(projectId, empno, projectRole);

		return "redirect:/project/members?projectId=" + projectId;
	}

	@GetMapping("/settings")
	public String settings(@RequestParam("projectId") int projectId, Model model) {
		Project project = projectService.findProjectById(projectId);
		List<Department> departmentList = departmentService.findDepartmentList();
		List<User> userList = userService.findUserList();
		Map<Integer, String> deptName = new HashMap<Integer, String>();
		
		for(Department d: departmentList) {
			deptName.put(d.getDeptno(), d.getName());
		}
		
		model.addAttribute("deptName", deptName);
		model.addAttribute("project", project);
		model.addAttribute("userList", userList);
		return "project/settings";
	}
	
	@PostMapping("update")
	public String updateProject(Project project, @RequestParam("projectId") int projectId, @RequestParam String action ) {
		project.setId(projectId);
		if("save".equals(action)) {
			projectService.modifyProject(project);
			return "redirect:/project/overview?projectId=" + projectId;
		} else if("delete".equals(action)) {
			reportService.removeReportByProjectId(projectId);
			List<Task> taskList = taskService.findTaskListByProjectId(projectId);
			for(Task t: taskList) {
				taskAssigneeService.removeTaskAssigneeByTaskId(t.getId());
				taskService.removeTask(t.getId());
			}
			projectMemberService.removeProjectMemberByProjectId(projectId);
			projectService.removeProject(projectId);
			
			return "redirect:/projects";
		}else {
			return "redirect:/project/settings?projectId=" + projectId;
		}
		
		
		
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {

		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);
		return "project/create";
	}

}
