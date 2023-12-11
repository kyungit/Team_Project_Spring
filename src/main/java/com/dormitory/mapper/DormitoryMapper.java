package com.dormitory.mapper;

import com.dormitory.dto.DormitoryDTO;
import com.dormitory.dto.RoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DormitoryMapper {
    public List<RoomDTO> getRoom() throws Exception;
}
