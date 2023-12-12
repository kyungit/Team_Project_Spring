package com.dormitory.service;

import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.RoomDTO;

import java.time.LocalDate;
import java.util.List;


public interface DormitoryService {

    //==============1. MAIN==================

    //1. 숙소 추천(별점)
    public List<DormitoryDTO> getDormitoryStar(String keyword);


    //
    public List<RoomDTO> getRoomDetail(String d_code);



    //====================5. SearchList=================
    // 숙소 정보 조회 (페이지 기주)
    public List<DormitoryDTO> getDormitoryList(int pageNum, String keyword, String type, String star, LocalDate startDate, LocalDate endDate) throws Exception;

}
