package com.dormitory.controller;

import com.dormitory.dto.ReviewDTO;
import com.dormitory.dto.RoomReviewDTO;
import com.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RestRoomInfoController {
    private final DormitoryService service;

    //1. 숙소 제일 위, 방 전체 정보 출력(사진, 리뷰 등)
    @GetMapping("/roomInfo")
    public RoomReviewDTO getRoomDomitoryInfo(){
        //조인
        return null;
    }

    //리뷰 테이블
    @GetMapping("/review")
    public ReviewDTO getReview(){
        return null;
    }
}
