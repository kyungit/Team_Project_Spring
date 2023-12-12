package com.dormitory.controller;

import com.dormitory.dto.MemberDTO;
import com.dormitory.dto.ReservationDTO;
import com.dormitory.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class RestMenuController {
    private final MemberService service;

    //1. 회원 정보 조회
    @GetMapping("/memberInfo")
    public MemberDTO getMemberInfo(HttpSession session){
        String userid = (String)session.getAttribute("userid");
        return service.getMemberInfo(userid);
    }

    //2. 예약 정보 조회
    @GetMapping("/reservationInfo")
    public ReservationDTO getReservationInfo(HttpSession session){

        return null;
    }
    //3. 방문 내역
    @GetMapping("/visited")
    public ReservationDTO getVisited(HttpSession session){
        return null;
    }




}
