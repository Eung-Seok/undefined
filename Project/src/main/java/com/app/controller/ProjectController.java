package com.app.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.app.dto.attachment.Attachment;
import com.app.dto.project.Project;
import com.app.dto.projectMember.ProjectMember;
import com.app.dto.report.Report;
import com.app.dto.task.Task;
import com.app.dto.taskAssignee.TaskAssignee;
import com.app.dto.user.User;
import com.app.service.attachment.AttachmentService;
import com.app.service.board.BoardService;
import com.app.service.comment.CommentService;
import com.app.service.notification.NotificationService;
import com.app.service.project.ProjectService;
import com.app.service.projectMember.ProjectMemberService;
import com.app.service.report.ReportService;
import com.app.service.task.TaskService;
import com.app.service.taskAssignee.TaskAssigneeService;
import com.app.service.user.UserService;
import com.app.vo.attachment.AttachmentVO;

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
    private AttachmentService attachmentService;

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
	public String addTaskAction(@RequestParam("projectId") int projectId, Task task, HttpSession session) {
		// 1. 로그인 정보 확인 (구글 연동 시 userId 필요)
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null)
			return "redirect:/login"; // 예외 처리

		try {
			// 2. 업무 저장 + 구글 전송 + 일정 연동을 하나의 서비스 메서드에서 처리
			taskService.saveTaskWithGoogleSync(projectId, task, loginUser.getEmpno());
		} catch (Exception e) {
			e.printStackTrace();
			// 실패 시 에러 메시지를 들고 이동 (선택 사항)
			return "redirect:/project/tasks?projectId=" + projectId + "&error=sync_fail";
		}

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
		
		try {
	        // 복잡한 과정은 서비스가 처리
	        taskService.deleteTaskWithGoogleSync(taskId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 필요 시 에러 메시지 처리
	    }

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
		
		try {
	        // 비즈니스 로직은 서비스에서 한 번에!
	        taskService.modifyTaskWithGoogleSync(task, taskId);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
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
	    
	    List<Attachment> documentList = attachmentService.findAttachmentListByProject(projectId);
	    
	    model.addAttribute("project", project);
	    model.addAttribute("documentList", documentList); // JSP에서 사용될 리스트명
	    
	    return "project/docs";
	}

	@PostMapping("/docs/upload")
	public String uploadDoc(@RequestParam("file") MultipartFile file, 
	                        @RequestParam("category") String category,
	                        @RequestParam("projectId") int projectId,
	                        HttpSession session) throws Exception { // 세션에서 로그인 유저 ID를 가져오기 위해 추가

	    if (!file.isEmpty()) {
	        String uploadDir = "D:/upload/";
	        File dir = new File(uploadDir);
	        if (!dir.exists()) dir.mkdirs();

	        String originalFileName = file.getOriginalFilename();
	        // 실제 저장할 때는 파일명이 겹치지 않게 UUID 등을 쓰는 게 좋지만, 일단 현재 로직 유지
	        File dest = new File(uploadDir + originalFileName);
	        file.transferTo(dest);

	        // [중요] DB에 저장할 객체(VO) 생성
	        AttachmentVO attachment = new AttachmentVO();
	        attachment.setProjectId(projectId);
	        attachment.setCategory(category);
	        attachment.setOriginalFileName(originalFileName);
	        attachment.setFileName(originalFileName); 
	        attachment.setFileUrl(uploadDir + originalFileName);
	        
	        attachment.setUploaderUserId(1000); 

	        attachmentService.insertAttachment(attachment); 
	    }

	    return "redirect:/project/docs?projectId=" + projectId;
	}
	
	@GetMapping("/download")
	public void downloadFile(@RequestParam("fileId") int fileId, HttpServletResponse response) throws Exception {
	    // 1. DB에서 파일 정보 가져오기 (원본 파일명, 저장된 경로 등)
	    Attachment fileInfo = attachmentService.findAttachmentById(fileId);
	    
	    if (fileInfo != null) {
	        File file = new File(fileInfo.getFileUrl()); // 파일이 저장된 실제 경로 (D:/upload/파일명)

	        if (file.exists()) {
	            // 2. 브라우저에게 "이건 다운로드용 파일이야"라고 알려주는 설정
	            String encodedName = UriUtils.encode(fileInfo.getOriginalFileName(), "UTF-8");
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedName + "\"");
	            response.setContentType("application/octet-stream");
	            response.setContentLength((int) file.length());

	            // 3. 실제 파일을 읽어서 브라우저로 복사 (FileCopyUtils는 스프링 제공 툴)
	            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	            FileCopyUtils.copy(inputStream, response.getOutputStream());
	        }
	    }
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
