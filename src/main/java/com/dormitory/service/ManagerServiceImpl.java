package com.dormitory.service;


import com.dormitory.dto.*;
import com.dormitory.mapper.DormitoryMapper;
import com.dormitory.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper mapper;


    @Override
    public void managerUpdate(String userid,String dormitoryCode) throws Exception {
        mapper.managerUpdate(userid,dormitoryCode);
    }

    @Override
    public List<ReservationDTO> getManagerR(String d_code) throws Exception {
       return mapper.getManagerR(d_code);
    }

    @Override
    public void ReservationCheckin(int reservation_code) throws Exception {
        mapper.ReservationCheckin(reservation_code);
    }

    @Override
    public void ReservationCheckout(int reservation_code) throws Exception {
        mapper.ReservationCheckout(reservation_code);
    }

    @Override
    public List<DormitoryDTO> getDcode() throws Exception {
        return mapper.getDcode();
    }
}