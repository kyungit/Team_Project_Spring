package com.dormitory.service;


import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.RoomDTO;
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
    public List<DormitoryDTO> getDormitoryList(int pageNum, String keyword, String type, String star, LocalDate startDate,LocalDate endDate) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("pageNum",pageNum);
        data.put("keyword",keyword);
        data.put("type",type);
        data.put("star",star);
        data.put("startDate",startDate);
        data.put("endDate",endDate);

        System.out.println(pageNum);
        System.out.println(keyword);
        System.out.println(type);
        System.out.println(star);
        System.out.println(startDate);
        System.out.println(endDate);

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
    public List<RoomDTO> getRoomDetail(String d_code){
        return mapper.getRoomDetail(d_code);

    }
}
