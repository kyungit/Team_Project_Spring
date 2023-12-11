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

    //search
    // 메인화면 숙소 정보 조회 / list
    @GetMapping("/list")
    public List<DormitoryDTO> getDormitoryList(@RequestParam("pageNum") int pageNum) throws Exception{
        return service.getDormitoryList(pageNum*10);
    }

    //추천 숙소 (-> 무슨 정보로 추천 할건지는 아직 고민)
    @GetMapping("/recommend")
    public List<DormitoryDTO> getRecommend(@RequestParam("pageNum") int pageNum) throws Exception{
        return service.getDormitoryList(pageNum*10);
    }










}
