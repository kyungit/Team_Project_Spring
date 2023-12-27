package com.dormitory.service;


import com.dormitory.dto.*;
import com.dormitory.mapper.DormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DormitoryServiceImpl implements DormitoryService {

    @Autowired
    DormitoryMapper mapper;
    // 메인화면 숙소 정보 조회
    //=====================MAIN======================

    @Override
    public List<DormitoryDTO> getDormitoryList(int pageNum, String keyword, List<String> typeList, int minStar, int maxStar,
    LocalDate startDate, LocalDate endDate) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("pageNum",pageNum);
        data.put("keyword",keyword);
        data.put("typeList",typeList);
        data.put("minStar",minStar);
        data.put("maxStar",maxStar);
        data.put("startDate",startDate);
        data.put("endDate",endDate);

        // System.out.println(pageNum);
        // System.out.println(keyword);
        // System.out.println(typeList);
        // System.out.println(minStar);
        // System.out.println(maxStar);
        // System.out.println(startDate);
        // System.out.println(endDate);

        List<DormitoryDTO> list = mapper.getDormitoryList(data);
        if(list == null) System.out.println("null");
        else System.out.println("not null");
        System.out.println(list);
        //System.out.println("디비 접속 = " + list.get(0).getD_name());

        return mapper.getDormitoryList(data);
    }

    //리뷰 높은 순  추천
    @Override
    public List<DormitoryDTO> getDormitoryByStar(String keyword) {
        return mapper.getDormitoryByStar(keyword);
    }

    //5.(test)얼리체크인 순
    @Override
    public List<DormitoryDTO> getEarlyCheckin() {
        return mapper.getEarlyCheckin();
    }
    //6.(test)
    @Override
    public List<DormitoryDTO> getDormitoryByGrade() {
        return mapper.getDormitoryByGrade();
    }

    //7.
    @Override
    public List<DormitoryDTO> getDormitoryByType(String type) {
        return mapper.getDormitoryByType(type);
    }




    //===========================




    //=====================4. RoomInfo
    //1.숙소
    @Override
    public List<DormitoryRoomDTO> getRoomReviewInfo(String d_code) {
        return mapper.getRoomReviewInfo(d_code);
    }
    //2. 리뷰 정보(숙소 전체)

    @Override
    public List<ReviewDTO> getReview(String d_code) {
        return mapper.getReview(d_code);
    }

    //3. 지도 위도/경도
    public DormitoryDTO getMap(String d_code){
        return mapper.getMap(d_code);

    }
    //4. 숙소별 객실 정보
    public List<RoomTypeDTO> getR_Code(String d_code){
        return mapper.getR_Code(d_code);
    }

    @Override
    public List<String> getUrl(RoomTypeDTO room) {
        return mapper.getUrl(room);
    }
    public List<ReservationDTO> getReservationInfoByR_Code(String r_code){
        return mapper.getReservationInfoByR_Code(r_code);
    }


    //5.숙소 정보
    @Override
    public List<DormitoryDTO> getDormitory(String d_code) {
        return mapper.getDormitory(d_code);
    }
    //6. 숙소별 어메니티 정보
    public List<AmenityDTO> getAmenity(String d_code){
        return mapper.getAmenity(d_code);
    }
    //7. 취소 정책

    @Override
    public List<CancelDTO> getCancelPolicy(DormitoryDTO dormitory) {
        return mapper.getCancelPolicy(dormitory);
    }

    public Integer getRoomCount(String r_code) {
        return mapper.getRoomCount(r_code);
    }
}
