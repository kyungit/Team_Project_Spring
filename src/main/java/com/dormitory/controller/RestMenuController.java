package com.dormitory.controller;

import com.dormitory.dto.MemberDTO;
import com.dormitory.dto.ReservationDTO;
import com.dormitory.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000/" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class RestMenuController {
    private final MemberService service;

    //1. 회원 정보 조회 -> OK
    @GetMapping("/memberInfo")
    public MemberDTO getMemberInfo(HttpSession session){
        String userid = (String)session.getAttribute("userid");
        //테스트용
        //return service.getMemberInfo("ehrud");

        return service.getMemberInfo(userid);
    }

    //2. 예약 정보 조회 -> OK
    @GetMapping("/reservationInfo")
    public List<ReservationDTO> getReservationInfo(HttpSession session){
        String userid = (String)session.getAttribute("userid");

        //테스트용
        //return service.getReservationInfoById("ehrud");

        return service.getReservationInfoById(userid);
    }
    //3. 방문 내역 -> OK
    @GetMapping("/visited")
    public List<ReservationDTO> getVisited(HttpSession session){
        String userid = (String)session.getAttribute("userid");
        //테스트용
        //return service.getVisited("ehrud");
        return service.getVisited(userid);
    }




}
