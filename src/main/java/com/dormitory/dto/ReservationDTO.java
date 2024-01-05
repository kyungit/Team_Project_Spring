package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDTO {
    private int reservation_code;
    private String d_code;
    private String r_code;
    private String d_name;
    private String d_type;
    private String r_img;
    private String r_name;

    private String m_userid;
    private String m_telno;
    private String m_username;
    private String room;

    private LocalDate reservation_checkin;
    private LocalDate reservation_checkout;
    private int reservation_guest;
    private int reservation_price;
    private String reservation_description;
    private String d_discount;
    private int s_status;


    //tbl_member
    private String dormitoryCode;
    private String userid;
}
