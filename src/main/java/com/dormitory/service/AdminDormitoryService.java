package com.dormitory.service;

import com.dormitory.dto.PaymentDTO;

import java.math.BigDecimal;

public interface AdminDormitoryService {

    static void updatePayment(Long id, String name, BigDecimal amount) {};

    static void cancelPayment(Long id) {};

}
