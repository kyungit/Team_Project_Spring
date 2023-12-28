package com.dormitory.mapper;

import com.dormitory.dto.PaymentDTO;
import java.util.List;

public interface AdminDormitoryMapper {
    // 특정 사용자의 결제 정보를 가져오는 메소드
    PaymentDTO getPaymentByUserId(int userId);

    // 특정 거래의 결제 정보를 가져오는 메소드
    PaymentDTO getPaymentByTransactionId(int transactionId);

    // 모든 결제 정보를 가져오는 메소드
    List<PaymentDTO> getAllPayments();

    // 특정 사용자의 모든 결제 정보를 가져오는 메소드
    List<PaymentDTO> getPaymentsByUserId(int userId);
}
