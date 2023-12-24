package com.dormitory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin("http://localhost:3000/")
@Controller
public class testController {

    @GetMapping("/papa/exex")
    public void getExex(){}

//    @GetMapping("/oauth/google/callback")
//    public MemberDTO
}
