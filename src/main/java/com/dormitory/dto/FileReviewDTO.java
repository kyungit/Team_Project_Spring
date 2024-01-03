package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileReviewDTO {
    //FileDTO
    public int fileseqno;
    public int file_review_code;
    public String review_file;
    public String review_stored;
    public long review_filesize;
    public String userid;
    public String checkfile;
    //ReviewDTO
    private int review_code;
    private String d_code;
    private String r_code;
    private String m_userid;
    private String review_score;
    private String review_comment;
    private int reservation_code;
}
