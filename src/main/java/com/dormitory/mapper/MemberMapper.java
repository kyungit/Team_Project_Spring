package com.dormitory.mapper;

import com.dormitory.dto.CancelDTO;
import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.MemberDTO;
import com.dormitory.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    //====================2. Menu===================

    //1. 회원정보 조회
    public MemberDTO getMemberInfo(String userid);


    //2. 예약 정보 조회
    public List<ReservationDTO> getReservationInfoById(String userid);

    //3. 예약 상태변경
    public void ReservationOut(int reservation_code) throws Exception;

    //4. 방문 내역 조회
    public List<ReservationDTO> getVisited(String userid);

    //5. 방문 내역 삭제
    public void DeleteVisited(int reservation_code) throws Exception;
    //=================3. Reservation=================

    //1. 숙소+객실 정보
    public List<DormitoryDTO> getDormitoryRoom(String d_code);

    //2. 구매 회원 정보 등록
    //3-2. 예약 정보 저장(POST)
    public List<ReservationDTO> posteservationInfo(String r_code);
    //4. 결제
    //5. 취소 정책
    public List<CancelDTO> getCancelPolicy(String d_code);




    //============밑에 건들ㄴㄴ================

    public MemberDTO memberInfo(String m_userid);

    public void memberSave(MemberDTO member);
}
