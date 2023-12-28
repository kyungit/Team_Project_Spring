package com.dormitory.service;

import com.dormitory.dto.PaymentDTO;

import java.math.BigDecimal;
import java.util.List;

public interface AdminDormitoryService {

    void updatePayment(Long id, String name, BigDecimal amount);

    void cancelPayment(Long id);

    List<PaymentDTO> getPayment();

    PaymentDTO confirmPayment(Long id);
}
