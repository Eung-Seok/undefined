package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() { return "dashboard"; }

    @GetMapping("/projects")
    public String projects() { return "projects"; }

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
