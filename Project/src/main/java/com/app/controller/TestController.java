package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class TestController {

	@Autowired
	UserService userService;
	
	@GetMapping("/test")
	public String test(Model model) {
		List<User> userList = userService.findUserList();
		model.addAttribute("userList",userList);
		
		return "user";
		
	}
}
