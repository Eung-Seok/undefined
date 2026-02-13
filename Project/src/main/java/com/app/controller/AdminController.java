package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/users") public String users(){ return "admin/users"; }
    @GetMapping("/roles") public String roles(){ return "admin/roles"; }
    @GetMapping("/org") public String org(){ return "admin/org"; }
    @GetMapping("/system") public String system(){ return "admin/system"; }
}
