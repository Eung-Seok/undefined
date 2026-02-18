package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.app.dto.member.MemberDTO;
import com.app.service.MemberService;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    // 1. 로그인 화면 (views/login/login.jsp)
    @GetMapping("/login")
    public String loginForm() {
        // 폴더 경로인 login/를 추가했습니다.
        return "login/login"; 
    }

    // 2. 회원가입 화면 (views/signup/signup.jsp)
    @GetMapping("/signup")
    public String signupForm() {
        // 폴더 경로인 signup/를 추가했습니다.
        return "signup/signup"; 
    }

    // 3. 회원가입 처리 로직
    @PostMapping("/signup")
    public String register(MemberDTO dto) {
        // 서비스 계층을 통해 DB 저장
        memberService.registerMember(dto);
        
        // 가입 완료 후 로그인 페이지로 리다이렉트
        return "redirect:/login"; 
    }
}