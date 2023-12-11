package com.dormitory.service;

import com.dormitory.dto.MemberDTO;
import com.dormitory.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper mapper;
    @Override
    public MemberDTO getMemberInfo(String userid) {

        return mapper.getMemberInfo(userid);
    }
}
