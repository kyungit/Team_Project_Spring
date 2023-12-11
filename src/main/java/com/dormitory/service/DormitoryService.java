package com.dormitory.service;

import com.dormitory.dto.DormitoryDTO;

import java.util.List;


public interface DormitoryService {
    // 메인화면 숙소 정보 조회
    public List<DormitoryDTO> getDormitoryList(int pageNum) throws Exception;

}
