package com.dormitory.service;

import com.dormitory.dto.*;
import com.dormitory.mapper.MemberMapper;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

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
    //5. 방문/예약 내역 삭제
    public void DeleteVisited(int reservation_code) throws Exception;
    //6. 리뷰 등록
    public void setReview(ReviewDTO review) throws  Exception;
    //7. 사용자가 작성한 리뷰 조회
    public List<ReviewDTO> getMemberReview(String userid) throws  Exception;
    //8. 리뷰 사진등록
    public void setImages(Map<String,Object> data) throws Exception;
    public String getReviewCode();
    // 리뷰 수정하기
    public void modifyReview(ReviewDTO review) throws Exception;
    // 리뷰 수정 시 파일 정보 수정
    public void deleteFileList(int fileseqno) throws Exception;
    // 사진 목록 가져오기
    public List<FileDTO> imagesInfoview(int file_review_code) throws Exception;
    public List<String> getFilename() throws  Exception;
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

    // 6. 예약 가능한 방 찾기
    public List<String> getAvailableRooms(ReservationDTO reservation);



    //===========밑에는 건들ㄴㄴ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//service.memberInfo(authentication.getName());
    public MemberDTO memberInfo(String email);

    public void memberSave(MemberDTO member);

    //토큰용
    public MemberDTO getMemberInfoByToken(String token);

    public void setToken(String token);


}
