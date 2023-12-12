package com.dormitory.controller;

import com.dormitory.dto.DormitoryDTO;
import com.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RestHomeController {
    private final DormitoryService service;

    //=========================MAIN==================================

    //지역별 숙소(keyword를 파라미터로 제공받기)
    @GetMapping("/location")
    public void getLocation(){

    }


    //1. 추천 숙소 (별점 높은 순)
    //10개 가져오기
    @GetMapping("/star")
    public List<DormitoryDTO> getStar(@RequestParam(name="keyword",defaultValue="",required=false) String keyword){
        return service.getDormitoryStar(keyword);
    }


    //2. 추천 숙소 (특가???????)
    //10개 가져오기
    @GetMapping("/discount")
    public List<DormitoryDTO> getDiscount(@RequestParam(name="pageNum",defaultValue="1",required=false) int pageNum) throws Exception{
        return null;
    }










}
