package com.dormitory.controller;

import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.RoomDTO;
import com.dormitory.service.DomitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestDormitoryController {


    private final DomitoryService service;

    @GetMapping("/rest/room")
    public List<RoomDTO> getExample() throws Exception{

        return service.getRoom();
    }



}
