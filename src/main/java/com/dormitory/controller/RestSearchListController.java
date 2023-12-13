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

    //1. 검색 화면에서 왠쪽 체크박스(?) 프론트에서 체크박스 onChange 마다 -> 검색 다시 조회 ( /searchList/dormitory ) -> 할거없음


    //2. 검색 화면에서 숙소 정보 -> like 조건 어떻게 할건지 회의
    @GetMapping("/dormitory")
    public List<DormitoryDTO> getDormitory(
            @RequestParam(name="pageNum",defaultValue="1",required=false) int pageNum
            ,@RequestParam(name="keyword",defaultValue="",required=false) String keyword
            ,@RequestParam(name="type", defaultValue="",required=false) String type
            ,@RequestParam(name="star", defaultValue="", required = false) String star
            ,@RequestParam(name="startDate", required = false) LocalDate startDate
            ,@RequestParam(name="endDate", required = false) LocalDate endDate) throws Exception{

        if(startDate==null) startDate = LocalDate.now();
        if(endDate==null) endDate = LocalDate.now().plusDays(1);

        return service.getDormitoryList(pageNum*10,keyword,type,star,startDate,endDate);
    }


}
