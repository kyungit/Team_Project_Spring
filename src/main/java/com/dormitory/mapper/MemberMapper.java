package com.dormitory.mapper;

import com.dormitory.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public MemberDTO getMemberInfo(String userid);
}
