package com.dormitory.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
    private String keyword;
    private String[] type;
    private String[] star;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guest;

    public List<String> getTypeList() {
    return Arrays.asList(type);
}

    public List<String> getStarList() {
        return Arrays.asList(star);
    }
}

