package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dto.user.User;
import com.app.service.mypage.MyPageService;

@Controller
@RequestMapping("/mypage")

public class MyPageController {
	@Autowired
    MyPageService myPageService;

    @GetMapping("/today")
    public String today(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");

        model.addAttribute("list",
                myPageService.getMyTodayTask(user.getEmpno()));

        return "mypage/today_task";
    }
}