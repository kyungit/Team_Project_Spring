package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {
    private String d_code;
    private long r_code;
    private int s_status;
    private String m_userid;
    private String telno;
}
