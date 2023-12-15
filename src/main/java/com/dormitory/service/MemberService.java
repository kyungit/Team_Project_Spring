package com.dormitory.service;

import com.dormitory.dto.CancelDTO;
import com.dormitory.dto.MemberDTO;
import com.dormitory.dto.ReservationDTO;

import java.util.List;

public interface MemberService {

    //====================2. Menu===================

    //1. 회원정보 조회
    public MemberDTO getMemberInfo(String userid);

    //2. 예약 정보 조회
    public List<ReservationDTO> getReservationInfoById(String userid);
    //3. 예약 상태 변경
    public void ReservationOut(int reservation_code) throws Exception;
    //4. 방문 내역 조회
    public List<ReservationDTO> getVisited(String userid);
    //5. 방문 내역 삭제
    public void DeleteVisited(int reservation_code) throws Exception;
    //=================3. Reservation=================

    //1. 숙소+객실 정보
    //2. 구매 회원 정보 등록
    //3-1. 예약 정보?????? 등록?? 인데/???? 위랑 어떻게합치지
    //4. 결제
    //5. 취소 정책
    public List<CancelDTO> getCancelPolicy(String d_code);
}
