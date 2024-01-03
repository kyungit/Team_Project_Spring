package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {
    public int fileseqno;
    public int file_review_code;
    public String review_file;
    public String review_stored;
    public long review_filesize;
    public String userid;
    public String checkfile;
    public String file_img;
}
