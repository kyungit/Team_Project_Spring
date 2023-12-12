package com.dormitory.controller;

import com.dormitory.dto.DormitoryDTO;
import com.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/searchList")
public class RestSearchListController {


    private final DormitoryService service;

    //1. 검색 화면에서 왠쪽 체크박스(?)


    //2. 검색 화면에서 숙소 정보
    @GetMapping("/dormitory")
    public List<DormitoryDTO> getDormitory(
            @RequestParam(name="pageNum",defaultValue="1",required=false) int pageNum
            ,@RequestParam(name="keyword",defaultValue="",required=false) String keyword
            ,@RequestParam(name="type", defaultValue="",required=false) String type
            ,@RequestParam(name="star", defaultValue="", required = false) String star
            ,@RequestParam(name="startDate", required = false) LocalDate startDate
            ,@RequestParam(name="endDate", required = false) LocalDate endDate) throws Exception{

        if(null==startDate) startDate = LocalDate.now();
        if(null==endDate) endDate = LocalDate.now();

        System.out.println("keyword : " + keyword);


        return service.getDormitoryList(pageNum*10,keyword,type,star,startDate,endDate);
    }


}
