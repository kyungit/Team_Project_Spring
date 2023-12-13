package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

    private String review_code;
    private String d_code;
    private String r_code;
    private String m_userid;
    private String review_score;
    private String review_comment;
    private String review_file;

}
