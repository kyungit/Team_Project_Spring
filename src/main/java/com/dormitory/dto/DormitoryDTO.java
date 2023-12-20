package com.dormitory.dto;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class DormitoryDTO {
    private String d_code;
    private String d_name;
    private String d_telno;
    private String d_checkout;
    private String d_checkin;
    private String d_img;
    private String d_type;
    private int d_zipcode;
    private String d_road;
    private String d_lat;
    private String d_lon;
    private String d_description;
    private String d_grade;
    private String d_star;

    private String max_r_price;
    private String min_r_price;
}
