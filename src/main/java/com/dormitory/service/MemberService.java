package com.dormitory.service;

import com.dormitory.dto.*;

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
    public DormitoryRoomDTO getDormitoryRoom(String d_code);

    //2. 구매 회원 정보 등록
    //3-2. 예약 정보 저장(POST)

    public void postReservation(ReservationDTO reservation);
    //4. 결제
    public void getPayment(PaymentDTO paymentDTO)throws Exception;
    //4-1. 결제 환불
    public void getPaymentCancel(String merchant_uid)throws Exception;
    //5. 취소 정책
    public List<CancelDTO> getCancelPolicy(DormitoryDTO dormitory);





    //===========밑에는 건들ㄴㄴ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//service.memberInfo(authentication.getName());
    public MemberDTO memberInfo(String email);

    public void memberSave(MemberDTO member);


}
