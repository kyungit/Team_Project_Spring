package com.dormitory.controller;

import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.SearchDTO;
import com.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/searchList")
public class RestSearchListController {


    private final DormitoryService service;

    //1. 검색 화면에서 왠쪽 체크박스(?) 프론트에서 체크박스 onChange 마다 -> 검색 다시 조회 ( /searchList/dormitory ) -> 할거없음


    //2. 검색 화면에서 숙소 정보 -> like 조건 어떻게 할건지 회의
    @PostMapping("/dormitory")
    public List<DormitoryDTO> getDormitory(@RequestBody SearchDTO search, @RequestParam(name="pageNum",defaultValue="1",required=false) int pageNum) throws Exception {

        System.out.println("keyword : " + search.getKeyword());
        for (int i = 0; i < search.getType().length; i++) {
            System.out.println("type : " + i + search.getType()[i]);
        }
        for (int i = 0; i < search.getStar().length; i++) {
            System.out.println("star : " + i + search.getStar()[i]);
        }
        System.out.println("startdate : " +search.getStartDate());
        System.out.println("enddate : " +search.getEndDate());
        System.out.println("guest : " + search.getGuest());

        // 별점 리스트를 처리하여 최소 별점과 최대 별점을 계산합니다.
        List<String> starList = search.getStarList();
        int minStar = 5;
        int maxStar = 10;

        if (!starList.contains("All") && !starList.isEmpty()) {
            List<Integer> intStarList = starList.stream().map(Integer::parseInt).collect(Collectors.toList());

            if (intStarList.size() == 1) { // 별점이 단일 값으로 선택되었을 때
                int star = intStarList.get(0);
                minStar = star == 6 ? 5 : star - 1;
                maxStar = star;
            } else { // 별점이 여러 값으로 선택되었을 때
                minStar = intStarList.contains(6) && intStarList.contains(7) && intStarList.contains(8) && intStarList.contains(9) && intStarList.contains(10) ? 5 : Collections.min(intStarList) - 1;
                maxStar = Collections.max(intStarList);
            }
        }

        return service.getDormitoryList(pageNum * 10 - 10, search.getKeyword(), search.getTypeList(), minStar, maxStar, search.getStartDate(), search.getEndDate());
    }

    // @GetMapping("/dormitory")
    // public List<DormitoryDTO> getDormitory(
    //         @RequestParam(name="pageNum",defaultValue="1",required=false) int pageNum
    //         ,@RequestParam(name="keyword",defaultValue="",required=false) String keyword
    //         ,@RequestParam(name="type", defaultValue="",required=false) String[] type
    //         ,@RequestParam(name="star", defaultValue="", required = false) String[] star
    //         ,@RequestParam(name="startDate", required = false) LocalDate startDate
    //         ,@RequestParam(name="endDate", required = false) LocalDate endDate
    //         ,@RequestParam(name="guest", required=false, defaultValue = "2") int guest) throws Exception{

    //         System.out.println("pageNum : " + pageNum);
    //         System.out.println("keyword : " + keyword);
    //         for (int i = 0; i < type.length; i++) {
    //             System.out.println("type : " + i + type[i]);
    //         }
    //         for (int i = 0; i < star.length; i++) {
    //             System.out.println("star : " + i + star[i]);
    //         }
    //         System.out.println("startDate : " + startDate);
    //         System.out.println("endDate : " + endDate);
    //         System.out.println("guest : " + guest);

    //         return service.getDormitoryList(pageNum * 10 - 10, keyword, type, star, startDate, endDate);
    // }


}