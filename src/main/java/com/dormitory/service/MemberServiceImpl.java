package com.dormitory.service;

import com.dormitory.dto.*;
import com.dormitory.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.File;
import java.util.List;
import java.util.Map;

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

    //5. 방문/예약 내역 삭제
    @Override
    public void DeleteVisited(int reservation_code) throws Exception {
        mapper.DeleteVisited(reservation_code);
    }
    //6. 리뷰 등록
    @Override
    public void setReview(ReviewDTO review) throws Exception {
        mapper.setReview(review);
    }
    //7. 사용자가 작성한 리뷰 조회
    @Override
    public List<ReviewDTO> getMemberReview(String userid) throws Exception {
        return mapper.getMemberReview(userid);
    }
    //8. 리뷰 사진등록
    @Override
    public void setImages(Map<String, Object> data) throws Exception {
        mapper.setImages(data);
    }

    @Override
    public String getReviewCode() {
        return mapper.getReviewCode();
    }

    // 리뷰 수정하기
    @Override
    public void modifyReview(ReviewDTO review) throws Exception {
        mapper.modifyReview(review);
    }
    // 리뷰 수정 시 파일 정보 수정
    @Override
    public void deleteFileList(int fileseqno) throws Exception {
        mapper.deleteFileList(fileseqno);
    }

    @Override
    public List<FileDTO> imagesInfoview(int file_review_code) throws Exception {
        return mapper.imagesInfoview(file_review_code);
    }

    @Override
    public List<String> getFilename() throws Exception {
        return mapper.getFilename();
    }
    /*@Transactional
    public void insertReviewAndFile(ReviewDTO review, FileDTO file) {
        try {
            mapper.setReview(review);
            file.setReview_Code(Integer.parseInt(review.getReview_code()));
            mapper.setImages((Map<String, Object>) file);
        } catch (Exception e) {
            // 트랜잭션 롤백 처리
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }*/

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

    // 6. 예약 가능한 방 찾기
    @Override
    public List<String> getAvailableRooms(ReservationDTO reservation) {
        return mapper.getAvailableRooms(reservation);
    }


    //====================밑에는 건들ㄴㄴ=================

    public MemberDTO memberInfo(String m_userid) {
        // return mapper.memberInfo(userid);
        return mapper.memberInfo(m_userid);
    }

    public void memberSave(MemberDTO member){
        mapper.memberSave(member);
    }

    //토큰용

    @Override
    public MemberDTO getMemberInfoByToken(String token) {
        return mapper.getMemberInfoByToken(token);
    }

    @Override
    public void setToken(String token) {
        mapper.setToken(token);
    }
}
