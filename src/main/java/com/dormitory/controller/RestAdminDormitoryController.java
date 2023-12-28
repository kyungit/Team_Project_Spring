package com.dormitory.controller;

import com.dormitory.dto.PaymentDTO;
import com.dormitory.service.AdminDormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000/" })
@RestController
@RequestMapping("/components") // 클래스 레벨에서 기본 경로 설정
@RequiredArgsConstructor
public class RestAdminDormitoryController {

    private final AdminDormitoryService adminDormitoryService; // 서비스 인스턴스 주입

    @PutMapping("/{d_code}")
    public String updateDormitory(@PathVariable String d_code, @RequestBody String dormitory) {
        // TODO: 숙박시설 정보 수정 로직 구현
        return null;
    }

    @GetMapping("/components/payment/payment")
    public List<PaymentDTO> getPayment() {
        // 결제 정보 조회 서비스 메소드 호출
        return adminDormitoryService.getPayment();
    }

    @PostMapping("/components/payment/payment/{paymentId}/confirm")
    public PaymentDTO confirmPayment(@PathVariable Long paymentId) {
        // 결제 확정 서비스 메소드 호출
        return adminDormitoryService.confirmPayment(paymentId);
    }

    @PutMapping("/components/payment/payment/{paymentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePayment(@PathVariable Long paymentId, @RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        BigDecimal amount = new BigDecimal((String) payload.get("amount"));
        adminDormitoryService.updatePayment(paymentId, name, amount);
        return ResponseEntity.ok().body("Payment updated successfully");
    }

    @PostMapping("/components/payment/payment/{paymentId}/cancel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> cancelPayment(@PathVariable Long paymentId) {
        adminDormitoryService.cancelPayment(paymentId);
        return ResponseEntity.ok().body("Payment cancelled successfully");
    }

}


/*
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/{paymentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePayment(@PathVariable Long paymentId, @RequestBody Payment newPayment) {
        paymentService.updatePayment(paymentId, newPayment);
        return ResponseEntity.ok(new ApiResponse(true, "Payment updated successfully"));
    }

    @PostMapping("/{paymentId}/cancel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> cancelPayment(@PathVariable Long paymentId) {
        paymentService.cancelPayment(paymentId);
        return ResponseEntity.ok(new ApiResponse(true, "Payment cancelled successfully"));
    }
}
*/



