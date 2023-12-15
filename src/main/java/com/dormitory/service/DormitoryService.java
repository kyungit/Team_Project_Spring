package com.dormitory.service;

import com.dormitory.dto.*;

import java.time.LocalDate;
import java.util.List;


public interface DormitoryService {

    //==============1. MAIN==================

    //2. 숙소 추천(별점)
    public List<DormitoryDTO> getDormitoryByStar(String keyword);


    //5.
    public List<DormitoryDTO> getEarlyCheckin();

    // 6.(test) 등급별
    public List<DormitoryDTO> getDormitoryByGrade();

    //7.
    public List<DormitoryDTO> getDormitoryByType(String type);


    //========================4. RoomInfo=========================
    //1. 숙소
    public List<DormitoryRoomDTO> getRoomReviewInfo(String d_code);
    //2. 리뷰 정보(숙소 전체)
    public List<ReviewDTO> getReview(String d_code);
    //3. 지도 위도/경도
    public DormitoryDTO getMap(String d_code);

    //4. 숙소별 객실 조회
    public List<RoomDTO> getRoomDetail(String d_code);

    //5. 숙소 정보
    public List<DormitoryDTO> getDormitory(String d_code);
    //6. 어메니티 정보
    public List<AmenityDTO> getAmenity(String d_code);



    //====================5. SearchList=================
    // 숙소 정보 조회 (페이지 기주)
    public List<DormitoryDTO> getDormitoryList(int pageNum, String keyword, String type, String star, LocalDate startDate, LocalDate endDate) throws Exception;

}
