package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDTO {
    private String d_code;
    private long r_code;
    private String d_name;
    private String d_type;
    private String r_img;
    private String r_name;

    private String m_userid;
    private String m_telno;

    private LocalDate reservation_checkin;
    private LocalDate reservation_checkout;
    private int reservation_guest;
    private int reservation_price;
    private String reservation_description;
    private int s_status;
}
