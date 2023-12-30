package com.dormitory.controller;

import com.dormitory.dto.*;
import com.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Map<String, Object>> getMap(@RequestParam(name="swLat",defaultValue="37.44687884419707",required=false) Double swLat,
                                            @RequestParam(name="swLng",defaultValue="126.63192835841528",required=false) Double swLng,
                                            @RequestParam(name="neLat",defaultValue="37.706582563426885",required=false) Double neLat,
                                            @RequestParam(name = "neLng", defaultValue = "127.28965306363936", required = false) Double neLng,
                                            @RequestParam(name = "centerLat", required = false) String centerLat,
                                            @RequestParam(name = "centerLng", required = false) String centerLng) {

        List<DormitoryDTO> dormitoryList = service.getMap(swLat, swLng, neLat, neLng);

        // 2. 가져온 데이터를 일정한 간격으로 나누어 각 그룹에서 하나씩 선택합니다. 이를 통해 서로 거리가 멀게 데이터를 선택할 수 있습니다.
        DormitoryDTO center = new DormitoryDTO();
        center.setD_lat(centerLat);
        center.setD_lon(centerLng);
        List<DormitoryDTO> sortedDormitoryList = dormitoryList.stream()
                .sorted((dorm1, dorm2) -> {
                    Double distance1 = calculateDistance(dorm1, center);
                    Double distance2 = calculateDistance(dorm2, center);
                    return Double.compare(distance1, distance2);
                })
                .collect(Collectors.toList());

        // 3. 선택된 데이터 중 최대 50개만 사용합니다.
        List<DormitoryDTO> selectedDormitoryList = new ArrayList<>();
        int interval = sortedDormitoryList.size() / 50;
        if (interval < 1) interval = 1;  // sortedDormitoryList의 크기가 50 미만인 경우를 처리합니다.

        for (int i = 0; i < sortedDormitoryList.size(); i += interval) {
            selectedDormitoryList.add(sortedDormitoryList.get(i));
            if (selectedDormitoryList.size() >= 50) {
                break;
            }
        }

        // selectedDormitoryList를 사용하여 결과를 생성합니다.
        List<Map<String, Object>> result = selectedDormitoryList.stream().map(dorm -> {
            Map<String, Object> map = new HashMap<>();
            Map<String, Double> latLng = new HashMap<>();

            latLng.put("lat", Double.valueOf(dorm.getD_lat()));
            latLng.put("lng", Double.valueOf(dorm.getD_lon()));

            map.put("d_name", dorm.getD_name());
            map.put("d_star", dorm.getD_star());
            map.put("position", latLng);

            return map;
        }).collect(Collectors.toList());

        return result;

    }

    private Double calculateDistance(DormitoryDTO dorm1, DormitoryDTO dorm2) {
        final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

        Double lat1 = Double.parseDouble(dorm1.getD_lat());
        Double lon1 = Double.parseDouble(dorm1.getD_lon());
        Double lat2 = Double.parseDouble(dorm2.getD_lat());
        Double lon2 = Double.parseDouble(dorm2.getD_lon());

        Double dLat  = Math.toRadians((lat2 - lat1));
        Double dLong = Math.toRadians((lon2 - lon1));

        Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLong/2) * Math.sin(dLong/2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return EARTH_RADIUS * c * 1000; // convert to meters
    }

//    //4. 객실 정보
//    @GetMapping("/roomDetail")
//    public Map<String, Object> getRoomDetail(DormitoryDTO dormitory, ReservationDTO reservation){
//        System.out.println(reservation.getReservation_checkin());
//        System.out.println(reservation.getReservation_checkout());
//        String d_code = dormitory.getD_code();
//        List<RoomTypeDTO> list = service.getR_Code(d_code); //d_code에 해당하는 r_code조회
//        Map<String,Object> data = new HashMap<>();
//        System.out.println("1111111 : " + list.size());
//
//
//
//        for(int i=0;i<list.size();i++){
//            RoomTypeDTO roomCode = list.get(i);
//            List<RoomTypeDTO> roomTypeList = service.getUrl(roomCode);
//
//
//
//            for(int k=0;k<roomTypeList.size();k++){
//                RoomTypeDTO room = roomTypeList.get(k);
//                //해당 날짜에 예약된 날짜가 있는지 계산하기
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                //db에서 가져온 모든 checkin날짜/checkout날짜
//                List<ReservationDTO> DB_reservation =service.getReservationInfoByR_Code(room.getR_code());
//                //원하는 날짜의 범위
//                LocalDate reservation_checkin = reservation.getReservation_checkin();
//                LocalDate reservation_checkout = reservation.getReservation_checkout();
//                System.out.println("DB checkinDate : " + DB_reservation.size());
//                if(DB_reservation.size() != 0){
//                    for(int j=0;j<DB_reservation.size();j++){
//                        LocalDate checkinDate = DB_reservation.get(j).getReservation_checkin();
//                        LocalDate checkoutDate = DB_reservation.get(j).getReservation_checkout();
//                                                                                                                                                                                                                                              for(LocalDate date = reservation_checkin; !date.isAfter(reservation_checkout);date = date.plusDays(1)){
//
//                            if(((date.isEqual(checkinDate) || date.isAfter(checkinDate)) && (date.isBefore(checkoutDate)))){
//                                room.setR_status("X");
//                                break;
//                            }
//                            else room.setR_status("O");
//
//                        }
//                    }
//                }
//                else {
//                    room.setR_status("O");
//                }
//
////                  data.put(room.getR_code(),service.getUrl(room));    //-> status 안들어감
//                data.put(room.getR_code(),room);                    //-> status 들어감
//            }
//        }
//        return data;
//    }

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
            List<String> li = service.getUrl(room);
            room.setR_url(li);

            data.put(room.getR_code(),room);
        }

        return data;
    }

    private Map<String, String> checkRoomAvailability(List<RoomTypeDTO> list, ReservationDTO reservation) {
        Map<String, String> availability = new HashMap<>();

        for (RoomTypeDTO room : list) {
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