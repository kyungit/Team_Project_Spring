package com.dormitory.controller;

import com.dormitory.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
