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

    //1. 지역별 숙소 -> 숙소 클릭시 바로 SearchList페이지로 이동이라 get/post 필요 x


    // 2. 추천 숙소 (별점 높은 순) + 3. 가능하면 내 주변위주로 -> OK
    //10개 가져오기 // -> 나중에 위치기반 넣으면 그때 keyword 이용
    @GetMapping("/star")
    public List<DormitoryDTO> getStar(@RequestParam(name="keyword",defaultValue="",required=false) String keyword){
        return service.getDormitoryByStar("화곡");
    }


    //4. 추천 숙소 (특가???????) -> 할인적용 어떻게 할건지 고민
    //10개 가져오기
    @GetMapping("/discount")
    public List<DormitoryDTO> getDiscount() throws Exception{
        return null;
    }

    //TEST
    //5. test용. 얼리체크인
    @GetMapping("/earlyCheckin")
    public List<DormitoryDTO> getEarlyCheckin()  {

        return service.getEarlyCheckin();
    }

    //6. test용. 등급별
    @GetMapping("/grade")
    public List<DormitoryDTO> getDormitoryByGrade()  {
        return service.getDormitoryByGrade();
    }

    //7. test용. 유형별
    @GetMapping("/type")
    public List<DormitoryDTO> getDormitoryByType(@RequestParam(name="type", defaultValue="",required = false)String type )  {

        //return service.getDormitoryByType(type);
        return service.getDormitoryByType("Hotel");
    }











}
