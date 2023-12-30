package com.dormitory.mapper;

import com.dormitory.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DormitoryMapper {


    //숙소 정보 전체 조회
    //=============================MAIN==========================

    //1. 숙소 추천 (별점 별)
    public List<DormitoryDTO> getDormitoryByStar();
    //2. 숙소 추천

    // 3. 특가 할인 숙소
    public List<DormitoryDTO> getDormitoryByDiscount();

    //5.(test)  추천 얼리체크인
    public List<DormitoryDTO> getEarlyCheckin();
    
    //6. (test) 추천 등급별
    public List<DormitoryDTO> getDormitoryByGrade();

    //7,
    public List<DormitoryDTO> getDormitoryByType (String type);


    //=======================2


    //=====================3

    //========================4 RoomInfo
    //1. 숙소
    public List<DormitoryRoomDTO> getRoomReviewInfo(String d_code);
    //2. 숙소별 리뷰 조회
    public List<ReviewDTO> getReview(String d_code);
    //3. 지도 위도/경도
    public List<DormitoryDTO> getMap(Map<String, Object> data);
    //4. 숙소별 객실 정보
    public List<RoomTypeDTO> getR_Code(String d_code);
    public List<ReservationDTO> getReservationInfoByR_Code(String r_code);
    public List<String> getUrl(RoomTypeDTO room);


    //5. 숙소 정보
    public List<DormitoryDTO> getDormitory(String d_code);
    //6. 숙소별 어메니티 정보
    public List<AmenityDTO> getAmenity(String d_code);
    //7. 취소 정책
    public List<CancelDTO> getCancelPolicy(DormitoryDTO dormitory);
    
    public Integer getRoomCount(String r_code); 

    //SearchList
    //전체 리스트
    public List<DormitoryDTO> getDormitoryList(Map<String, Object> data) throws Exception;



}
