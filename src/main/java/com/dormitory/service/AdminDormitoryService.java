package com.dormitory.service;

import com.dormitory.dto.PaymentDTO;

import java.math.BigDecimal;

public interface AdminDormitoryService {

    void updatePayment(Long id, String name, BigDecimal amount);

    void cancelPayment(Long id);
}
