package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @GetMapping("/overview") public String overview(){ return "project/overview"; }
    @GetMapping("/tasks") public String tasks(){ return "project/tasks"; }
    @GetMapping("/calendar") public String calendar(){ return "project/calendar"; }
    @GetMapping("/wbs") public String wbs(){ return "project/wbs"; }
    @GetMapping("/issues") public String issues(){ return "project/issues"; }
    @GetMapping("/docs") public String docs(){ return "project/docs"; }
    @GetMapping("/members") public String members(){ return "project/members"; }
    @GetMapping("/settings") public String settings(){ return "project/settings"; }
}
