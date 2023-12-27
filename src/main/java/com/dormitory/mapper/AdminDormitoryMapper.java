package com.dormitory.mapper;

import com.dormitory.dto.PaymentDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface AdminDormitoryMapper {

    @Update("UPDATE payment SET name=#{payment.name}, amount=#{payment.amount} WHERE id=#{id}")
    void updatePayment(@Param("id") Long id, @Param("payment") PaymentDTO payment);

    @Update("UPDATE payment SET status='CANCELLED' WHERE id=#{id}")
    void cancelPayment(@Param("id") Long id);
}
