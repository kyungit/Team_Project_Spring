package com.dormitory.service;

import com.dormitory.dto.PaymentDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdminDormitoryServiceImpl implements AdminDormitoryService {
    private final JdbcTemplate jdbcTemplate;

    public AdminDormitoryServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void updatePayment(Long id, String name, BigDecimal amount) {
        jdbcTemplate.update("UPDATE payment SET name=?, amount=? WHERE id=?", name, amount, id);
    }

    @Override
    public void cancelPayment(Long id) {
        jdbcTemplate.update("UPDATE payment SET status='CANCELLED' WHERE id=?", id);
    }
}

