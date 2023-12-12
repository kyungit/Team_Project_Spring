package com.dormitory.controller;

import com.dormitory.dto.*;
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
    @GetMapping("/roomReview")
    public RoomReviewDTO getRoomReviewInfo(){
        //조인
        return null;
    }

    //2. 리뷰 테이블
    @GetMapping("/review")
    public ReviewDTO getReview(){
        return null;
    }

    //3. 지도
    @GetMapping("/map")
    public DormitoryDTO getMap(){
        return null;

    }

    //4. 객실 정보
    @GetMapping("/roomDetail")
    public RoomDTO getRoomDetail(){
        return null;
    }
    //5. 숙소 정보
    @GetMapping("/dormitory")
    public DormitoryDTO getDormitory(DormitoryDTO dormitory){
        return null;
    }
    //6. 비품 정보
    @GetMapping("/amentiy")
    public AmenityDTO getAmenity(DormitoryDTO dormitory){
        return null;
    }
}
