package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomReviewDTO {

    //방
    private String d_code;
    private int r_code;
    private String r_name;
    private int r_num;
    private int r_max_num;
    private String r_img;
    
    //리뷰
    private String review_code;
    private String review_score;
    private String review_comment;
}
