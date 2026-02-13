package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.dto.attachment.Attachment;
import com.app.dto.board.Board;
import com.app.dto.calendarEvent.CalendarEvent;
import com.app.dto.comment.Comment;
import com.app.dto.department.Department;
import com.app.dto.issue.Issue;
import com.app.dto.notification.Notification;
import com.app.dto.post.Post;
import com.app.dto.project.Project;
import com.app.dto.projectMember.ProjectMember;
import com.app.dto.report.Report;
import com.app.dto.role.Role;
import com.app.dto.task.Task;
import com.app.dto.taskAssignee.TaskAssignee;
import com.app.dto.taskStatusHistory.TaskStatusHistory;
import com.app.dto.user.User;
import com.app.dto.userRole.UserRole;
import com.app.service.attachment.AttachmentService;
import com.app.service.board.BoardService;
import com.app.service.calendarEvent.impl.CalendarEventService;
import com.app.service.comment.CommentService;
import com.app.service.department.DepartmentService;
import com.app.service.issue.IssueService;
import com.app.service.notification.NotificationService;
import com.app.service.post.PostService;
import com.app.service.project.ProjectService;
import com.app.service.projectMember.ProjectMemberService;
import com.app.service.report.ReportService;
import com.app.service.role.RoleService;
import com.app.service.task.TaskService;
import com.app.service.taskAssignee.TaskAssigneeService;
import com.app.service.taskStatusHistory.TaskStatusHistoryService;
import com.app.service.user.UserService;
import com.app.service.userRole.UserRoleService;

@Controller
public class TestController {

	@Autowired
	UserService userService;

	@GetMapping("/test")
	public String test(Model model) {
		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);

		return "user";

	}

	@Autowired
	UserRoleService userRoleService;

	@GetMapping("/test1")
	public String test1(Model model) {
		List<UserRole> userRoleList = userRoleService.findUserRoleList();
		model.addAttribute("userRoleList", userRoleList);

		return "userRole";

	}

	@Autowired
	TaskStatusHistoryService taskStatusHistoryService;

	@GetMapping("/test2")
	public String test2(Model model) {
		List<TaskStatusHistory> taskStatusHistoryList = taskStatusHistoryService.findTaskStatusHistoryList();
		model.addAttribute("taskStatusHistoryList", taskStatusHistoryList);

		return "taskStatusHistory";

	}

	@Autowired
	TaskAssigneeService taskAssigneeService;

	@GetMapping("/test3")
	public String test3(Model model) {
		List<TaskAssignee> taskAssigneeList = taskAssigneeService.findTaskAssigneeList();
		model.addAttribute("taskAssigneeList", taskAssigneeList);

		return "taskAssignee";

	}

	@Autowired
	TaskService taskService;

	@GetMapping("/test4")
	public String test4(Model model) {
		List<Task> taskList = taskService.findTaskList();
		model.addAttribute("taskList", taskList);

		return "task";

	}

	@Autowired
	RoleService roleService;

	@GetMapping("/test5")
	public String test5(Model model) {
		List<Role> roleList = roleService.findRoleList();
		model.addAttribute("roleList", roleList);

		return "role";

	}

	@Autowired
	ReportService reportService;

	@GetMapping("/test6")
	public String test6(Model model) {
		List<Report> reportList = reportService.findReportList();
		model.addAttribute("reportList", reportList);

		return "report";

	}

	@Autowired
	ProjectMemberService projectMemberService;

	@GetMapping("/test7")
	public String test7(Model model) {
		List<ProjectMember> projectMemberList = projectMemberService.findProjectMemberList();
		model.addAttribute("projectMemberList", projectMemberList);

		return "projectMember";

	}

	@Autowired
	ProjectService projectService;

	@GetMapping("/test8")
	public String test8(Model model) {
		List<Project> projectList = projectService.findProjectList();
		model.addAttribute("projectList", projectList);

		return "project";

	}

	@Autowired
	PostService postService;

	@GetMapping("/test9")
	public String test9(Model model) {
		List<Post> postList = postService.findPostList();
		model.addAttribute("postList", postList);

		return "post";

	}

	@Autowired
	NotificationService notificationService;

	@GetMapping("/test10")
	public String test10(Model model) {
		List<Notification> notificationList = notificationService.findNotificationList();
		model.addAttribute("notificationList", notificationList);

		return "notification";

	}

	@Autowired
	IssueService issueService;

	@GetMapping("/test11")
	public String test11(Model model) {
		List<Issue> issueList = issueService.findIssueList();
		model.addAttribute("issueList", issueList);

		return "issue";

	}

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/test12")
	public String test12(Model model) {
		List<Department> departmentList = departmentService.findDepartmentList();
		model.addAttribute("departmentList", departmentList);

		return "department";

	}

	@Autowired
	CommentService commentService;

	@GetMapping("/test13")
	public String test13(Model model) {
		List<Comment> commentList = commentService.findCommentList();
		model.addAttribute("commentList", commentList);

		return "comment";

	}

	@Autowired
	CalendarEventService calendarEventService;

	@GetMapping("/test14")
	public String test14(Model model) {
		List<CalendarEvent> calendarEventList = calendarEventService.findCalendarEventList();
		model.addAttribute("calendarEventList", calendarEventList);

		return "calendarEvent";

	}

	@Autowired
	BoardService boardService;

	@GetMapping("/test15")
	public String test15(Model model) {
		List<Board> boardList = boardService.findBoardList();
		model.addAttribute("boardList", boardList);

		return "board";

	}

	@Autowired
	AttachmentService attachmentService;

	@GetMapping("/test16")
	public String test16(Model model) {
		List<Attachment> attachmentList = attachmentService.findAttachmentList();
		model.addAttribute("attachmentList", attachmentList);

		return "attachment";

	}

}
