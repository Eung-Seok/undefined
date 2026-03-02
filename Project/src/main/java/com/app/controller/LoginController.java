package com.app.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.user.User;
import com.app.service.api.GoogleCalendarService;
import com.app.service.calendarEvent.CalendarEventService;
import com.app.service.role.RoleService;
import com.app.service.user.UserService;
import com.app.service.userRole.UserRoleService;
import com.google.api.services.calendar.model.Event;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    GoogleCalendarService googleCalendarService;
    @Autowired
    CalendarEventService calendarEventService;

    // [1] 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login/login"; 
    }

    // [2] 로그인 실행
    @PostMapping("/login")
	public String loginProcess(User user, HttpSession session, Model model) {

		User loginUser = userService.findUserByEmpno(user.getEmpno());

		if (loginUser != null && loginUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("loginUserRole", roleService.findRoleById(userRoleService.findUserRoleByUserId(user.getEmpno()).getRoleId()).getName());
			
			try {
	            List<Event> googleEventList = googleCalendarService.getUpcomingEvents();
	            calendarEventService.syncWithGoogle(loginUser.getEmpno(), googleEventList);
	            System.out.println("구글 캘린더 동기화 성공");
	        } catch (IOException | GeneralSecurityException e) {
	            // 동기화 실패 시 로그만 남기고 로그인은 계속 진행
	            System.err.println("구글 동기화 중 오류 발생: " + e.getMessage());
	            // 필요한 경우 사용자에게 "동기화 실패" 메시지를 세션에 살짝 담아둘 수 있습니다.
	        }
			
			return "redirect:/dashboard";
		} else {

			model.addAttribute("loginError", "사원번호 또는 비밀번호가 잘못되었습니다.");
			return "login/login";
		}
	}
    //  회원가입 페이지 
    @GetMapping("/signup")
    public String signupPage() {
        return "signup/signup"; 
    }

    //  회원가입 실행
    @PostMapping("/signup")
    public String signupProcess(User user, Model model) {
        
        User checkUser = userService.findUserByEmpno(user.getEmpno());

        if (checkUser != null) {
            
            model.addAttribute("msg", "이미 등록된 사원번호입니다. (사번: " + user.getEmpno() + ")");
            model.addAttribute("url", "/signup");
            return "common/alert"; 
        }

        try {
            userService.saveUser(user);
            return "redirect:/login";
        } catch (Exception e) {           
            model.addAttribute("signupError", "가입 처리 중 오류가 발생했습니다.");
            return "signup/signup"; 
        }
    }

    //  비밀번호 찾기 
    @PostMapping("/find-password")
    public String findPassword(int empno, String email, Model model) {
        User user = userService.findUserByEmpno(empno);

        if (user != null && user.getEmail().equals(email)) {
            model.addAttribute("loginError", "회원님의 비밀번호는 [" + user.getPassword() + "] 입니다.");
        } else {
            model.addAttribute("loginError", "일치하는 정보가 없습니다.");
        }
        return "login/login"; 
    }
    

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
    	// 세션무효화
        session.invalidate();
        // 쿠키삭제 
        Cookie cookie = new Cookie("savedEmpno", null);
        cookie.setPath("/"); 
        cookie.setMaxAge(0); 
        response.addCookie(cookie);
        return "redirect:/login";
    }
}