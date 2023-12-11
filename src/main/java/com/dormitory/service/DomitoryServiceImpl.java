package com.dormitory.service;


import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.RoomDTO;
import com.dormitory.mapper.DormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomitoryServiceImpl implements DomitoryService{

    @Autowired
    DormitoryMapper mapper;

    @Override
    public List<RoomDTO> getRoom() throws Exception{

        return mapper.getRoom();
    }
}
