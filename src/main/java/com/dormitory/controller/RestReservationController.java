package com.dormitory.controller;

import com.dormitory.dto.*;
import com.dormitory.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000/" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class RestReservationController {
    private final MemberService service;

    //======================예약 화면

    //1. 숙소 + 객실 (JOIN + DTO)
    @GetMapping("/dormitoryRoom")
    public DormitoryRoomDTO getDormitoryRoom(RoomTypeDTO room) throws Exception{
        return service.getDormitoryRoom(room.getR_code());
    }


    //2. 구매 회원 정보 입력 받아서 POST 처리만 하면됨
    @PostMapping("/memberInfo")
    public String postMemberInfo() {
        return null;
    }

    //3-1. 예약 정보 조회  -> OK

    @GetMapping("/reservationInfo")
    public ReservationDTO getReservationInfo(ReservationDTO reservation){

        return reservation;
    }

    //3-2. 예약 정보 받아서 POST 처리
    @ResponseBody
    @PostMapping("/reservationInfo")
    public void posteservationInfo(@RequestBody ReservationDTO reservation){

        List<String> availableRooms = service.getAvailableRooms(reservation);
        for (String room : availableRooms) {
            System.out.println("방 뭐 들어가있게? " + room);
        }
            System.out.println("방 어디로 배정되게? : " + availableRooms.get(0));
            reservation.setRoom(availableRooms.get(0));
            service.postReservation(reservation);

    }



    //4. 결제 정보 저장 + 예약 상태 변경
    @Transactional
    @PostMapping("/payment")
    public String postPayment(@RequestBody PaymentDTO payment)throws Exception{
        System.out.println(payment.toString());
        System.out.println(payment);
        try {
            service.getPayment(payment);
            return "{\" message \" : \" GOOD \"}";
        }catch (Exception e){
            e.printStackTrace();
            return  "{\" message \" : \" FAIL \"}";
        }
    }
    //4-1. 결제 환불
    @PostMapping("/paymentCancel")
    public void postPaymentCancel()throws Exception{
        
        //임시
        service.getPaymentCancel("IMP151");
    }


    //5. 시즌별 환불규정 -> OK
    @GetMapping("/cancel")
    public Map<String, String> getCancel(DormitoryDTO dormitory) throws Exception{
        //테스트용
        //return service.getCancelPolicy("3002534");
        List<CancelDTO> cancel = service.getCancelPolicy(dormitory);
        String cancelPolicy1="성수기 규정 enter";
        String cancelPolicy0="비수기 규정 enter";
        for(int i=0;i<cancel.size();i++){
            if(cancel.get(i).getC_type() == 1){
                cancelPolicy1 += "예약일 "+cancel.get(i).getC_policy_apply_date()+"일 전, "
                        +cancel.get(i).getC_time()+"시 기준으로 "
                        +(100-cancel.get(i).getC_rate()) + "%만 환불됩니다.enter";
            }
            else if(cancel.get(i).getC_type() == 0){
                cancelPolicy0 += "예약일 "+cancel.get(i).getC_policy_apply_date()+"일 전, "
                        +cancel.get(i).getC_time()+"시 기준으로 "
                        +(100-cancel.get(i).getC_rate()) + "%만 환불됩니다.enter";
            }
        }
        Map<String, String> data = new HashMap<>();
        data.put("policy0",cancelPolicy0);
        data.put("policy1",cancelPolicy1);
        return data;
    }


}
