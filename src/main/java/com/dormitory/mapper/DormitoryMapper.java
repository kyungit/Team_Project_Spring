package com.dormitory.mapper;

import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.RoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DormitoryMapper {
    public List<RoomDTO> getRoom() throws Exception;

    //숙소 정보 전체 조회
    //=============================MAIN==========================
    //전체 리스트
    public List<DormitoryDTO> getDormitoryList(Map<String, Object> data) throws Exception;

    //1. 숙소 추천 (별점 별)
    public List<DormitoryDTO> getDormitoryStar(String keyword);
    //2. 숙소 추천





    //=======================2


    //=====================3

    //========================4
    public List<RoomDTO> getRoomDetail(String d_code);




}
