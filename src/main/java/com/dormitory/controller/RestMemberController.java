package com.dormitory.controller;

import com.dormitory.dto.MemberDTO;
import com.dormitory.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RestMemberController {

    private final MemberService service;
    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    //로그인 화면 보기
    @GetMapping("/member/login")
    public void getLogin() { }



}
