package com.dormitory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @GetMapping("/papa/exex")
    public void getExex(){}
}
