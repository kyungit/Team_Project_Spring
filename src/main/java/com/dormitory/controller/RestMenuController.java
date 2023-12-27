package com.dormitory.controller;

import com.dormitory.dto.MemberDTO;
import com.dormitory.dto.ReservationDTO;
import com.dormitory.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000/" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class RestMenuController {
    private final MemberService service;

    //1. 회원 정보 조회 -> OK
    @GetMapping("/memberInfo")
    public MemberDTO getMemberInfo(HttpSession session) {
        String userid = (String) session.getAttribute("userid");
//        System.out.println("userid : " + userid);
//        System.out.println(service.getMemberInfo(userid));
        return service.getMemberInfo(userid);
    }

    //2. 예약 정보 조회 -> OK
    @GetMapping("/reservationInfo")
    public List<ReservationDTO> getReservationInfo(HttpSession session) {
        String userid = (String) session.getAttribute("userid");
        return service.getReservationInfoById(userid);
    }

    //3. 예약 상태 변경
    @ResponseBody
    @PostMapping("/reservationInfo")
    public String postReservationInfo(@RequestParam("reservation_code") int reservation_code) throws Exception {

        //예약내역 삭제 진행
        service.ReservationOut(reservation_code);

        return "{\"message\":\"GOOD\"}";
    }
    //4. 방문 내역 -> OK
    @GetMapping("/visited")
    public List<ReservationDTO> getVisited(HttpSession session) {
        String userid = (String) session.getAttribute("userid");

        return service.getVisited(userid);
    }



    //5. 방문내역 삭제
    @Transactional
    @ResponseBody
    @PostMapping("/visited")
    public String postVisited(ReservationDTO reservation,@RequestParam("reservation_code") int reservation_code) throws Exception {
            //방문 내역 삭제
            service.DeleteVisited(reservation_code);
            return "{\"message\":\"GOOD\"}";
    }

    //6. d_code 입력
    @PostMapping("/codeInput/{code}")
    public String postDCode(@PathVariable String d_code) throws Exception {
        service.getDormitoryRoom(d_code);
        return d_code;
    }
}
