package com.dormitory.service;

import com.dormitory.dto.*;
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
    //3. 예약 상태 변경
    @Override
    public void ReservationOut(int reservation_code) throws Exception {

        mapper.ReservationOut(reservation_code);
    }
    //4. 방문 내역 조회

    @Override
    public List<ReservationDTO> getVisited(String userid) {
        return mapper.getVisited(userid);
    }

    //5. 방문 내역 삭제
    @Override
    public void DeleteVisited(int reservation_code) throws Exception {
        mapper.DeleteVisited(reservation_code);
    }

    //=================3. Reservation=================

    //1. 숙소+객실 정보
    public DormitoryRoomDTO getDormitoryRoom(String d_code) {
        return mapper.getDormitoryRoom(d_code);
    }

    //2. 구매 회원 정보 등록
    //3-2. 예약 정보 저장(POST)

    @Override
    public void postReservation(ReservationDTO reservation) {
        mapper.postReservation(reservation);
    }

    //4. 결제
    @Override
    public void getPayment(PaymentDTO paymentDTO) throws Exception {
        mapper.getPayment(paymentDTO);
    }
    //4-1. 결제 환불
    @Override
    public void getPaymentCancel(String merchant_uid) throws Exception {
        mapper.getPaymentCancel(merchant_uid);
    }

    //4. 결제
    //5. 취소 정책
    @Override
    public List<CancelDTO> getCancelPolicy(DormitoryDTO dormitory) {
        return mapper.getCancelPolicy(dormitory);
    }






    //====================밑에는 건들ㄴㄴ=================

    public MemberDTO memberInfo(String m_userid) {
        // return mapper.memberInfo(userid);
        return mapper.memberInfo(m_userid);
    }

    public void memberSave(MemberDTO member){
        mapper.memberSave(member);
    }
}
