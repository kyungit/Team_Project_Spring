package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelDTO {
    private String d_code;
    private int c_policy_apply_date;
    private int c_rate;
    private int c_time;
    private int c_type;
}
