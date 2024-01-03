package com.dormitory.controller;

import com.dormitory.dto.DormitoryDTO;
import com.dormitory.service.DormitoryService;
//import com.dormitory.util.JWTUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000/" })
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RestHomeController {
    private final DormitoryService service;

    //=========================MAIN==================================

    //1. 지역별 숙소 -> 숙소 클릭시 바로 SearchList페이지로 이동이라 get/post 필요 x
    
    private List<DormitoryDTO> getNearbyDormitories(DormitoryDTO center, List<DormitoryDTO> dormitoryList) {
        final double MAX_DISTANCE = 5000; // 5km를 미터 단위로 환산
        final int MAX_RESULTS = 8; // 반환할 최대 숙소 수

        return dormitoryList.stream()
                .filter(dorm -> calculateDistance(dorm, center) <= MAX_DISTANCE)
                .limit(MAX_RESULTS) // 결과를 최대 8개로 제한
                .collect(Collectors.toList());
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

    // 2. 추천 숙소 (별점 높은 순) + 3. 가능하면 내 주변위주로 -> OK
    //10개 가져오기 // -> 나중에 위치기반 넣으면 그때 keyword 이용
    @GetMapping("/star")
    public List<DormitoryDTO> getStar(
            @RequestParam(name = "lat", defaultValue ="37.49616859", required = false) String centerLat,
            @RequestParam(name = "lng", defaultValue = "127.0204826", required = false) String centerLng
    ) throws Exception {
        // 2. 가져온 데이터를 일정한 간격으로 나누어 각 그룹에서 하나씩 선택합니다. 이를 통해 서로 거리가 멀게 데이터를 선택할 수 있습니다.

        System.out.println("centerLat : " + centerLat);
        System.out.println("centerLng : " + centerLng);

        List<DormitoryDTO> dormitoryList = service.getDormitoryByStar();

        DormitoryDTO center = new DormitoryDTO();
        center.setD_lat(centerLat);
        center.setD_lon(centerLng);

        List<DormitoryDTO> nearbyDormitories = getNearbyDormitories(center, dormitoryList);

        return nearbyDormitories;
    }

    //4. 추천 숙소 (특가???????) -> 할인적용 어떻게 할건지 고민
    //10개 가져오기
    @GetMapping("/discount")
    public List<DormitoryDTO> getDiscount(
            @RequestParam(name = "lat", defaultValue ="37.49616859", required = false) String centerLat,
            @RequestParam(name = "lng", defaultValue = "127.0204826", required = false) String centerLng
    ) throws Exception {
        List<DormitoryDTO> dormitoryList = service.getDormitoryByDiscount();

        DormitoryDTO center = new DormitoryDTO();
        center.setD_lat(centerLat);
        center.setD_lon(centerLng);

        List<DormitoryDTO> nearbyDormitories = getNearbyDormitories(center, dormitoryList);

        return nearbyDormitories;
    }

    //TEST
    //5. test용. 얼리체크인
    @GetMapping("/earlyCheckin")
    public List<DormitoryDTO> getEarlyCheckin(
            @RequestParam(name = "lat", defaultValue ="37.49616859", required = false) String centerLat,
            @RequestParam(name = "lng", defaultValue = "127.0204826", required = false) String centerLng
    ) throws Exception {
        List<DormitoryDTO> dormitoryList = service.getEarlyCheckin();
       
        DormitoryDTO center = new DormitoryDTO();
        center.setD_lat(centerLat);
        center.setD_lon(centerLng);

        List<DormitoryDTO> nearbyDormitories = getNearbyDormitories(center, dormitoryList);

        return nearbyDormitories;
    }

    //6. test용. 등급별
    @GetMapping("/grade")
    public List<DormitoryDTO> getDormitoryByGrade()  {
        return service.getDormitoryByGrade();
    }




    //밑으로 건들ㄴㄴ
    //JWT 관리 객체 의존성 주입
//    private final JWTUtil jwtUtil;


}
