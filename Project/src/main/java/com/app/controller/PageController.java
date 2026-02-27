package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
	public String root(HttpSession session) {

		if (session.getAttribute("loginUser") != null) {
			return "redirect:/dashboard";
		}
		return "redirect:/login";
	}

	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		if (session.getAttribute("loginUser") == null)
			return "redirect:/login";
		return "dashboard";
	}

	@GetMapping("/projects")
	public String projects(HttpSession session) {
		if (session.getAttribute("loginUser") == null)
			return "redirect:/login";
		return "projects";
	}

	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}

	@GetMapping("/board")
	public String board() {
		return "board/board";
	}

	@GetMapping("/board/write")
	public String boardWrite() {
		return "board/board-write";
	}

	@GetMapping("/board/view")
	public String boardView() {
		return "board/board-view";
	}

	@GetMapping("/employees")
	public String employees() {
		return "employee/employees";
	}


	@GetMapping("/employees/view")
	public String employeeView() {
		return "employee/employee-view";
	}

	@GetMapping("/search")
	public String search() {
		return "search";
	}
}
