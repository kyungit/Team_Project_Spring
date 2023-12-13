package com.dormitory.service;

import com.dormitory.dto.CancelDTO;
import com.dormitory.dto.MemberDTO;
import com.dormitory.dto.ReservationDTO;
import com.dormitory.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper mapper;

    //====================2. Menu===================

    //1. 회원정보 조회

    @Override
    public MemberDTO getMemberInfo(String userid) {
        return mapper.getMemberInfo(userid);
    }

    //2. 예약 정보 조회
    @Override
    public List<ReservationDTO> getReservationInfoById(String userid) {
        return mapper.getReservationInfoById(userid);
    }
    //3. 방문 내역 조회

    @Override
    public List<ReservationDTO> getVisited(String userid) {
        return mapper.getVisited(userid);
    }

    //=================3. Reservation=================

    //1. 숙소+객실 정보
    //2. 구매 회원 정보 등록
    //3-1. 예약 정보?????? 등록?? 인데/???? 위랑 어떻게합치지
    //4. 결제
    //5. 취소 정책
    @Override
    public List<CancelDTO> getCancelPolicy(String d_code) {
        return mapper.getCancelPolicy(d_code);
    }
}
