package com.dormitory.controller;

import com.dormitory.dto.MemberDTO;
import com.dormitory.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class RestMenuController {
    private final MemberService service;

    //회원 정보 조회
    @GetMapping("/memberInfo")
    public MemberDTO getMemberInfo(HttpSession session){
        String userid = (String)session.getAttribute("userid");
        return service.getMemberInfo(userid);
    }




}
