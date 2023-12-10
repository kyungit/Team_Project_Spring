package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class MemberDTO {
    private String userid;
    private String password;
    private String username;
    private String birthdate;
    private String gender;
    private String telno;
    private String email;
    private Date regdate;
    private Timestamp lastlogindate;
    private Timestamp lastlogoutdate;
    private Timestamp lastpwdate;
    private int pwCheck;
    private String role;
    private String domitory_code;
    private String authkey;
}
