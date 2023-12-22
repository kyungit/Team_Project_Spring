package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
    private String merchant_uid;
    private int reservation_code;
    private String buyer_tel;
    private String buyer_name;
    private int paid_amount;
    private String paid_status;
    private String success;

}
