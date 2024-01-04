package com.dormitory.service;

import com.dormitory.dto.*;

import java.time.LocalDate;
import java.util.List;


public interface ManagerService {

    public void managerUpdate(String userid,String dormitoryCode) throws  Exception;
    public List<ReservationDTO> getManagerR(String d_code)throws  Exception;
    public void ReservationCheckin(int reservation_code)throws Exception;
    public void ReservationCheckout(int reservation_code)throws Exception;

    public List<DormitoryDTO> getDcode()throws Exception;
}