package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private UserService userService;

    /**
     * 마이페이지 메인
     */
    @GetMapping("")
    public String mypage(HttpSession session, Model model) {

        User loginUser = (User) session.getAttribute("loginUser");

        // 로그인 안된 경우 → 로그인 페이지 이동
        if (loginUser == null) {
            return "redirect:/login";
        }

        User user = userService.findUserByEmpno(loginUser.getEmpno());
        model.addAttribute("user", user);

        return "mypage/mypage";
    }

    /**
     * 내프로필 수정 (모달)
     */
    @PostMapping("/profile/update")
    public String updateProfile(User user, HttpSession session) {

        userService.modifyUser(user);

        session.setAttribute("loginUser",
                userService.findUserByEmpno(user.getEmpno()));

        return "redirect:/mypage";
    }

    /**
     * 비밀번호 변경 (모달)
     */
    @PostMapping("/security/password")
    public String changePassword(
            int empno,
            String oldPassword,
            String newPassword,
            HttpSession session,
            Model model) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        User user = userService.findUserByEmpno(empno);

        if (!user.getPassword().equals(oldPassword)) {
            model.addAttribute("user", user);
            model.addAttribute("error", "기존 비밀번호가 일치하지 않습니다.");
            return "mypage/mypage";
        }

        user.setPassword(newPassword);
        userService.modifyUser(user);

        return "redirect:/mypage";
    }
}