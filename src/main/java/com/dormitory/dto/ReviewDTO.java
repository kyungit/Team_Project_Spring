package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ReviewDTO {

    private int review_code;
    private String d_code;
    private String r_code;
    private String m_userid;
    private String r_name;
    private String review_score;
    private String review_comment;
    private int reservation_code;
    private List<FileDTO> fileInfo;

    /*// List<FileDTO> 필드 추가
    private List<FileDTO> fileInfo;

    // getter, setter 메서드 추가
    public List<FileDTO> getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(List<FileDTO> fileInfo) {
        this.fileInfo = fileInfo;
    }*/
    private String review_file;

}
