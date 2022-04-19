package com.study.springboardpractice1.controller;

import com.study.springboardpractice1.domain.dto.MemberDto;
import com.study.springboardpractice1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    HttpServletRequest request; // 요청객체

    @Autowired
    MemberService memberService;

    // 회원가입
    @GetMapping("/member/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/member/signupcontroller")
    public String signupcontroller(MemberDto memberDto) {
        memberService.signup(memberDto);
        return "redirect:/";
    }

    // 로그인
    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/member/logincontroller")
    public String logincontroller(@RequestParam("mid") String mid, @RequestParam("mpw") String mpw) {
        boolean result = memberService.login(mid, mpw);
        if (result) {
            return "redirect:/";
        } else {
            return "redirect:/member/login";
        }
    }

    // 마이페이지
    @GetMapping("/member/info")
    public String memberinfo(Model model) {
        HttpSession session = request.getSession();
        MemberDto loginDto = (MemberDto) session.getAttribute("logindto");
        MemberDto memberDto = memberService.getmemberDto(loginDto.getMno());
        model.addAttribute("memberDto",memberDto);
        return "member/info";
    }

    // 로그아웃
    @GetMapping("/member/logout")
    public String logout() {
        HttpSession session = request.getSession();
        session.setAttribute("logindto", null);
        return "redirect:/";
    }

}
