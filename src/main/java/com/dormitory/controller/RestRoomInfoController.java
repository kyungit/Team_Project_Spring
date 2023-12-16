package com.dormitory.controller;

import com.dormitory.dto.*;
import com.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RestRoomInfoController {
    private final DormitoryService service;

    //1. 숙소 제일 위, 방 전체 정보 출력(사진, 등)
    @GetMapping("/roomReview")
    public List<DormitoryRoomDTO> getRoomReviewInfo(DormitoryDTO dormitory){
        String d_code= dormitory.getD_code();
        //조인
        return service.getRoomReviewInfo(d_code);
    }

    //2. 리뷰 테이블
    @GetMapping("/review")
    public List<ReviewDTO> getReview(DormitoryDTO dormitory){
        String d_code= dormitory.getD_code();


        return  service.getReview(d_code);
    }

    //3. 지도 위도/경도
    @GetMapping("/map")
    public DormitoryDTO getMap(DormitoryDTO dormitory){

        String d_code = dormitory.getD_code();
        return service.getMap(d_code);

    }

    //4. 객실 정보
    @GetMapping("/roomDetail")
    public List<RoomDTO> getRoomDetail(DormitoryDTO dormitory){
        String d_code = dormitory.getD_code();
        return service.getRoomDetail(d_code);

    }
    //5. 숙소 정보
    @GetMapping("/dormitory")
    public List<DormitoryDTO> getDormitory(DormitoryDTO dormitory){
        String d_code = dormitory.getD_code();
        return service.getDormitory(d_code);
    }
    //6. 비품 정보
    @GetMapping("/amenity")
    public List<AmenityDTO> getAmenity(DormitoryDTO dormitory){
        String d_code = dormitory.getD_code();

        return service.getAmenity(d_code);
    }

    
}
