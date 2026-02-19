package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.user.User;
import com.app.service.role.RoleService;
import com.app.service.user.UserService;
import com.app.service.userRole.UserRoleService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	RoleService roleService;

	// 1. 로그인 화면 이동
	@GetMapping("/login")
	public String loginForm() {
		return "login/login";
	}

	// 2. 로그인 실행 로직
	@PostMapping("/login")
	public String loginProcess(User user, HttpSession session, Model model) {

		User dbUser = userService.findUserByEmpno(user.getEmpno());

		if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("loginUser", dbUser);
			return "redirect:/dashboard";
		} else {

			model.addAttribute("loginError", "사원번호 또는 비밀번호가 잘못되었습니다.");
			return "login/login";
		}
	}

	// 3. 회원가입 화면 이동
	@GetMapping("/signup")
	public String signupForm() {
		return "signup/signup";
	}

	// 4. 회원가입 실행 로직
	@PostMapping("/signup")
	public String signupProcess(User user) {
		System.out.println(user);
		int result = userService.saveUser(user);

		if (result > 0) {
			return "redirect:/login";
		} else {
			return "redirect:/signup";
		}
	}

	// 5. 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}