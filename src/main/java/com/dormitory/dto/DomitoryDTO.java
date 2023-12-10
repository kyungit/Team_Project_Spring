package com.dormitory.dto;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class DomitoryDTO {
    private String d_code;
    private String d_name;
    private String d_telno;
    private String d_checkout;
    private String d_checkin;
    private String d_img;
    private String d_type;
    private int d_zipcode;
    private String d_road;
    private long d_lat;
    private long d_log;
    private String d_description;
    private String d_grade;
}
