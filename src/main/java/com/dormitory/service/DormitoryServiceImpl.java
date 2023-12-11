package com.dormitory.service;


import com.dormitory.dto.DormitoryDTO;
import com.dormitory.mapper.DormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {

    @Autowired
    DormitoryMapper mapper;
    // 메인화면 숙소 정보 조회
    @Override
    public List<DormitoryDTO> getDormitoryList(int pageNum) throws Exception {
        return mapper.getDormitoryList(pageNum);
    }
}
