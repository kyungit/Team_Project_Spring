package com.dormitory.controller;

import com.dormitory.service.DomitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestDomitoryController {

    private final DomitoryService service;

    @GetMapping("/example")
    public void getExample(){}
}
