package com.app.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // [1] 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login/login"; 
    }

    // [2] 로그인 실행
    @PostMapping("/login")
    public String loginProcess(User user, String saveId, HttpServletResponse response, HttpSession session, Model model) {
        User dbUser = userService.findUserByEmpno(user.getEmpno());

        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("loginUser", dbUser);

            // 1. 쿠키 생성 (사번 저장)
            Cookie cookie = new Cookie("savedEmpno", String.valueOf(dbUser.getEmpno()));
            cookie.setPath("/");

            if (saveId != null) {
                // 사원번호 저장 체크 시: 7일 유지
                cookie.setMaxAge(60 * 60 * 24 * 7);
            } else {     
                cookie.setMaxAge(-1); // -1은 세션 쿠키로, 브라우저 종료 시 삭제됨
            }

            response.addCookie(cookie);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("loginError", "사원번호 또는 비밀번호가 일치하지 않습니다.");
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