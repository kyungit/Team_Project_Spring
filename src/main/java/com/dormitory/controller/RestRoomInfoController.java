package com.dormitory.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dormitory.dto.AmenityDTO;
import com.dormitory.dto.CancelDTO;
import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.DormitoryRoomDTO;
import com.dormitory.dto.ReservationDTO;
import com.dormitory.dto.ReviewDTO;
import com.dormitory.dto.RoomTypeDTO;
import com.dormitory.service.DormitoryService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = { "http://localhost:3000/" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/roomInfo")
public class RestRoomInfoController {
    private final DormitoryService service;

    //1. 숙소 제일 위, 방 전체 정보 출력(사진, 등)
    @GetMapping("/roomReview")
    public List<DormitoryRoomDTO> getRoomReviewInfo(DormitoryDTO dormitory) {
        String d_code = dormitory.getD_code();
        return service.getRoomReviewInfo(d_code);
    }

    //2. 리뷰 테이블
    @GetMapping("/review")
    public List<ReviewDTO> getReview(DormitoryDTO dormitory) {
        String d_code = dormitory.getD_code();
        return service.getReview(d_code);
    }

    //3. 지도 위도/경도
    @GetMapping("/map")
    public DormitoryDTO getMap(DormitoryDTO dormitory) {

        String d_code = dormitory.getD_code();
        return service.getMap(d_code);

    }

    //4. 객실 정보
    // @GetMapping("/roomDetail")
    // public Map<String, Object> getRoomDetail(DormitoryDTO dormitory, ReservationDTO reservation) {
    //     System.out.println(reservation.getReservation_checkin());
    //     System.out.println(reservation.getReservation_checkout());
    //     String d_code = dormitory.getD_code();
    //     List<RoomTypeDTO> list = service.getR_Code(d_code);
    //     Map<String, Object> data = new HashMap<>();

    //     for (int i = 0; i < list.size(); i++) {
    //         RoomTypeDTO roomCode = list.get(i);
    //         List<RoomTypeDTO> roomTypeList = service.getUrl(roomCode);

    //         for (int k = 0; k < roomTypeList.size(); k++) {
    //             RoomTypeDTO room = roomTypeList.get(k);
    //             //해당 날짜에 예약된 날짜가 있는지 계산하기
    //             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //             //db에서 가져온 모든 checkin날짜/checkout날짜
    //             List<ReservationDTO> DB_reservation = service.getReservationInfoByR_Code(room.getR_code());
    //             System.out.println("DB_reservation.size() : " + DB_reservation.size());
    //             //원하는 날짜의 범위
    //             LocalDate reservation_checkin = reservation.getReservation_checkin();
    //             LocalDate reservation_checkout = reservation.getReservation_checkout();

    //             if (DB_reservation.size() != 0) {
    //                 for (int j = 0; j < DB_reservation.size(); j++) {
    //                     LocalDate checkinDate = DB_reservation.get(j).getReservation_checkin();
    //                     LocalDate checkoutDate = DB_reservation.get(j).getReservation_checkout();
    //                     System.out.println("roomTypeList" + k + DB_reservation);
    //                     System.out.println("checkinDate : " + j + " " + checkinDate);
    //                     System.out.println("checkoutDate : " + j + " " + checkoutDate);

    //                     for (LocalDate date = reservation_checkin; !date.isAfter(reservation_checkout); date = date
    //                             .plusDays(1)) {
    //                         if ((date.isEqual(checkinDate) || date.isAfter(checkinDate))
    //                                 && date.isBefore(checkoutDate)) {
    //                             room.setR_status("X");
    //                             break;
    //                         } else {
    //                             room.setR_status("O");
    //                         }
    //                     }
    //                 }
    //             } else {
    //                 room.setR_status("O");
    //             }

    //             // data.put(room.getR_code(), service.getUrl(room)); // => status 안들어감
    //             data.put(room.getR_code(),room); // => status 들어감
    //         }

    //     }

    //     return data;
    // }


    // //4. 객실 정보
    @GetMapping("/roomDetail")
    public Map<String, Object> getRoomDetail(DormitoryDTO dormitory, ReservationDTO reservation) {
        System.out.println(reservation.getReservation_checkin());
        System.out.println(reservation.getReservation_checkout());
        String d_code = dormitory.getD_code();
        List<RoomTypeDTO> list = service.getR_Code(d_code); // d_code에 해당하는 모든 r_code 리스트를 뽑아냄.
        Map<String, Object> data = new HashMap<>();

        // 각 방의 예약 상태를 확인하는 로직을 별도의 메소드로 분리
        Map<String, String> roomStatusMap = checkRoomAvailability(list, reservation); // 모든 r_code에 해당하는 리스트와 예약 정보를 방 이용 가능

        // 각 방의 상세 정보와 예약 상태를 매핑
        for (RoomTypeDTO room : list) {
            room.setR_status(roomStatusMap.getOrDefault(room.getR_code(), "O"));
            // data.put(room.getR_code(), room);
            data.put(room.getR_code(), service.getUrl(room));
        }

        return data;
    }

    private Map<String, String> checkRoomAvailability(List<RoomTypeDTO> roomList, ReservationDTO reservation) {
        Map<String, String> availability = new HashMap<>();
        for (RoomTypeDTO room : roomList) {
            List<ReservationDTO> reservations = service.getReservationInfoByR_Code(room.getR_code()); // r_code에 해당하는 모든 예약 정보
            int maxRoomCount = service.getRoomCount(room.getR_code());
            int currentReservationCount = checkIfRoomAvailableCount(reservations, reservation);
            System.out.println("room.getR_code() : " + room.getR_code());
            System.out.println("maxRoomCount : " + maxRoomCount);
            System.out.println("currentReservationCount : " + currentReservationCount);
            String status = (currentReservationCount < maxRoomCount) ? "O" : "X"; // 기존의 모든 예약 정보와 비교해서 TRUE일 경우 예약 가능, FALSE일 경우 예약 중첩
            availability.put(room.getR_code(), status);
        }
        return availability;
    }

    private int checkIfRoomAvailableCount(List<ReservationDTO> reservations, ReservationDTO reservation) {
        LocalDate checkin = reservation.getReservation_checkin();
        LocalDate checkout = reservation.getReservation_checkout();

        int count = 0;
        for (ReservationDTO reserved : reservations) { // 기존의 모든 예약 정보와 비교
            if (!reserved.getReservation_checkout().isBefore(checkin)
                    && !reserved.getReservation_checkin().isAfter(checkout)) {
                count++; // 예약이 중첩됨
                
            }
        }
        return count; // 예약 가능
    }


    //5. 숙소 정보
    @GetMapping("/dormitory")
    public List<DormitoryDTO> getDormitory(DormitoryDTO dormitory) {
        String d_code = dormitory.getD_code();
        return service.getDormitory(d_code);
    }

    //6. 비품 정보
    @GetMapping("/amenity")
    public List<AmenityDTO> getAmenity(DormitoryDTO dormitory) {
        String d_code = dormitory.getD_code();
        return service.getAmenity(d_code);
    }

    @GetMapping("/cancel")
    public Map<String, String> getCancel(DormitoryDTO dormitory) {
        List<CancelDTO> cancel = service.getCancelPolicy(dormitory);
        String cancelPolicy1 = "성수기 규정 enter";
        String cancelPolicy0 = "비수기 규정 enter";
        for (int i = 0; i < cancel.size(); i++) {
            if (cancel.get(i).getC_type() == 1) {
                cancelPolicy1 += "예약일 " + cancel.get(i).getC_policy_apply_date() + "일 전, "
                        + cancel.get(i).getC_time() + "시 기준으로 "
                        + (100 - cancel.get(i).getC_rate()) + "%만 환불됩니다.enter";
            } else if (cancel.get(i).getC_type() == 0) {
                cancelPolicy0 += "예약일 " + cancel.get(i).getC_policy_apply_date() + "일 전, "
                        + cancel.get(i).getC_time() + "시 기준으로 "
                        + (100 - cancel.get(i).getC_rate()) + "%만 환불됩니다.enter";
            }
        }
        Map<String, String> data = new HashMap<>();
        data.put("policy0", cancelPolicy0);
        data.put("policy1", cancelPolicy1);
        return data;
    }

}
