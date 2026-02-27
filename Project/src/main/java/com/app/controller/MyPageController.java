package com.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.calendarEvent.CalendarEvent;
import com.app.dto.issue.Issue;
import com.app.dto.report.Report;
import com.app.dto.task.Task;
import com.app.dto.taskAssignee.TaskAssignee;
import com.app.dto.user.User;
import com.app.service.calendarEvent.CalendarEventService;
import com.app.service.issue.IssueService;
import com.app.service.report.ReportService;
import com.app.service.task.TaskService;
import com.app.service.taskAssignee.TaskAssigneeService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    /* ==================== Service ==================== */

    @Autowired private TaskService taskService;
    @Autowired private CalendarEventService calendarEventService;
    @Autowired private ReportService reportService;
    @Autowired private IssueService issueService;
    @Autowired
    private TaskAssigneeService taskAssigneeService;


    /* ==================== 공통 메서드 ==================== */

    private User getLoginUser(HttpSession session) {
        return (User) session.getAttribute("loginUser");
    }

    /* ==================== 마이페이지 메인 ==================== */

    @GetMapping("")
    public String mypage(Model model, HttpSession session) {

        User loginUser = getLoginUser(session);
        if (loginUser == null) return "redirect:/login";

        int empno = loginUser.getEmpno();

        // 오늘 할 일
        List<Task> todayTasks = taskService.getTodayTask(empno);
        model.addAttribute("todayTasks", todayTasks);
        
     // 프로젝트 공정율 (예: 첫 번째 프로젝트 기준)
        double projectProgress = taskService.calculateProjectProgress(1); 
        model.addAttribute("projectProgress", projectProgress);


        // 주간 캘린더
        List<CalendarEvent> weekEvents =
                calendarEventService.getWeekCalendarEvents(empno);
        model.addAttribute("weekEvents", weekEvents);

        model.addAttribute("todayScheduleCount",
                weekEvents.stream()
                          .filter(e -> e.getStartDate()
                          .equals(LocalDate.now().toString()))
                          .count());

        model.addAttribute("weekScheduleCount", weekEvents.size());

        // 주간 보고서
        List<Report> reports = reportService.findReportList();
        model.addAttribute("reports", reports);
        model.addAttribute("reportCount", reports.size());

        // 이슈 목록
        List<Issue> issues = issueService.findIssueList();
        model.addAttribute("issues", issues);
        model.addAttribute("issueCount", issues.size());

        return "mypage/mypage";
    }

    /* ==================== 오늘 할 일 ==================== */

    @GetMapping("/today")
    public String today(Model model, HttpSession session) {

        User loginUser = getLoginUser(session);
        if (loginUser == null) return "redirect:/login";

        List<Task> tasks =
                taskService.getTodayTask(loginUser.getEmpno());

        model.addAttribute("tasks", tasks);

        return "mypage/mytask-today";
    }

    @PostMapping("/project/updateProgress")
    public String updateProjectProgress(@RequestParam int projectId) {
        taskService.updateProjectProgress(projectId);
        return "redirect:/mypage"; // 갱신 후 마이페이지로 이동
    }

    @PostMapping("/today/updateStatus")
    public String updateStatus(@RequestParam int taskId,
                               @RequestParam String status) {
        taskService.updateTaskStatus(taskId, status);
        return "redirect:/mypage/today";
    }
    @PostMapping("/today/complete")
    public String completeTask(@RequestParam int taskId, HttpSession session) {
        if (getLoginUser(session) == null) return "redirect:/login";
        taskService.completeTask(taskId);
        return "redirect:/mypage/today";
    }


    /* ==================== 주간 캘린더 ==================== */

    @GetMapping("/calendar/week")
    public String weekCalendar(Model model, HttpSession session) {

        User loginUser = getLoginUser(session);
        if (loginUser == null) return "redirect:/login";

        List<CalendarEvent> weekEvents =
                calendarEventService.getWeekCalendarEvents(
                        loginUser.getEmpno());

        model.addAttribute("weekEvents", weekEvents);

        return "mypage/week-calendar";
    }

    /* ==================== 보고서 ==================== */

    @GetMapping("/report/{id}")
    public String reportDetail(@PathVariable int id, Model model) {
        model.addAttribute("report",
                reportService.findReportById(id));
        return "mypage/report-detail";
    }

    @GetMapping("/report/write")
    public String writeReportForm(Model model) {
        model.addAttribute("report", new Report());
        return "mypage/report-add";
    }

    @PostMapping("/report/add")
    public String addReport(Report report, HttpSession session) {

        User loginUser = getLoginUser(session);
        if (loginUser == null) return "redirect:/login";

        report.setAuthorUserId(loginUser.getEmpno());
        report.setCreatedAt(LocalDate.now().toString());

        reportService.saveReport(report);
        return "redirect:/mypage";
    }

    @GetMapping("/report/edit/{id}")
    public String editReportForm(@PathVariable int id, Model model) {
        model.addAttribute("report",
                reportService.findReportById(id));
        return "mypage/report-edit";
    }

    @PostMapping("/report/update")
    public String updateReport(Report report) {
        reportService.modifyReport(report);
        return "redirect:/mypage/report/" + report.getId();
    }

    @GetMapping("/report/delete/{id}")
    public String deleteReport(@PathVariable int id) {
        reportService.removeReport(id);
        return "redirect:/mypage";
    }

    /* ==================== 이슈 ==================== */

    @GetMapping("/issue")
    public String issueList(Model model) {
        List<Issue> issues = issueService.findIssueList();
        model.addAttribute("issues", issues);
        model.addAttribute("issueCount", issues.size());
        return "mypage/issue-list";
    }

    @GetMapping("/issue/write")
    public String writeIssueForm(Model model) {
        model.addAttribute("issue", new Issue());
        return "mypage/issue-add";
    }

    @PostMapping("/add")
    public String addIssue(Issue issue, HttpSession session) {

        User loginUser = getLoginUser(session);
        if (loginUser == null) return "redirect:/login";

        issue.setReporterUserId(loginUser.getEmpno());
        issue.setCreatedAt(LocalDate.now().toString());
        issue.setUpdatedAt(LocalDate.now().toString());

        issueService.saveIssue(issue);
        return "redirect:/issue";
    }

    @GetMapping("/issue/{id}")
    public String issueDetail(@PathVariable int id, Model model) {
        model.addAttribute("issue",
                issueService.findIssueById(id));
        return "mypage/issue-detail";
    }

    @GetMapping("/issue/edit/{id}")
    public String editIssueForm(@PathVariable int id, Model model) {
        model.addAttribute("issue",
                issueService.findIssueById(id));
        return "mypage/issue-edit";
    }

    @PostMapping("/update")
    public String updateIssue(Issue issue) {

        issue.setUpdatedAt(LocalDate.now().toString());
        issueService.modifyIssue(issue);

        return "redirect:/issue/" + issue.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteIssue(@PathVariable int id) {
        issueService.removeIssue(id);
        return "redirect:/issue";
    }
    /* ==================== 내 참여 업무 ==================== */

 // 내 참여 업무 목록
 @GetMapping("/task/assignees")
 public String myAssignees(Model model, HttpSession session) {
     User loginUser = getLoginUser(session);
     if (loginUser == null) return "redirect:/login";

     // 전체 담당자 목록
     List<TaskAssignee> allAssignees = taskAssigneeService.findTaskAssigneeList();

     // 로그인한 사용자만 필터링
     List<TaskAssignee> myAssignees = allAssignees.stream()
    	        .filter(a -> a.getUserId() == loginUser.getEmpno())
    	        .collect(Collectors.toList());


     model.addAttribute("myAssignees", myAssignees);
     return "mypage/mytask-assignee"; // JSP 카드
 }

 // 담당자 등록 (권한 필요)
 @PostMapping("/task/assignees/add")
 public String addAssignee(TaskAssignee assignee, HttpSession session) {
     User loginUser = getLoginUser(session);
     if (loginUser == null) return "redirect:/login";

     // 권한 체크 (예: ADMIN, PM만 가능)
     if (!"ADMIN".equals(loginUser.getRole()) && !"PM".equals(loginUser.getRole())) {
         return "redirect:/mypage/task/assignees?error=unauthorized";
     }

     assignee.setJoinedAt(LocalDate.now().toString());
     taskAssigneeService.saveTaskAssignee(assignee);
     return "redirect:/mypage/task/assignees";
 }

 // 담당자 삭제 (권한 필요)
 @GetMapping("/task/assignees/delete/{id}")
 public String deleteAssignee(@PathVariable int id, HttpSession session) {
     User loginUser = getLoginUser(session);
     if (loginUser == null) return "redirect:/login";

     if (!"ADMIN".equals(loginUser.getRole()) && !"PM".equals(loginUser.getRole())) {
         return "redirect:/mypage/task/assignees?error=unauthorized";
     }

     taskAssigneeService.removeTaskAssignee(id);
     return "redirect:/mypage/task/assignees";
 }


}