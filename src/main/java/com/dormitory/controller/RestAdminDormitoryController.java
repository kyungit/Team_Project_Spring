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
@RequiredArgsConstructor
public class RestAdminDormitoryController {

    @PutMapping("/{d_code}")
    public String updateDormitory(@PathVariable String d_code, @RequestBody String dormitory) {
        // 여기서 데이터베이스에서 코드에 맞는 숙박시설을 찾아 정보를 수정하고 반환합니다.
        // Facility는 숙박시설 정보를 담는 클래스입니다.
        return null;
    }

    @GetMapping
    @RequestMapping("/api/payment")
    public List<PaymentDTO> getPayment() {
        // 결제 정보를 가져와서 반환합니다.
        return null;
    }

    @PostMapping("/{paymentId}/confirm")
    public PaymentDTO confirmPayment(@PathVariable Long paymentId) {
        // 결제를 확정하고 그 정보를 반환합니다.
        return null;
    }

    @PutMapping("/{paymentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePayment(@PathVariable Long paymentId, @RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        BigDecimal amount = new BigDecimal((String) payload.get("amount"));
        AdminDormitoryService.updatePayment(paymentId, name, amount);
        return ResponseEntity.ok().body("Payment updated successfully");
    }

    @PostMapping("/{paymentId}/cancel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> cancelPayment(@PathVariable Long paymentId) {
        AdminDormitoryService.cancelPayment(paymentId);
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



