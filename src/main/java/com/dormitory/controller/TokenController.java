package com.dormitory.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000/" })
@RestController
public class TokenController {


//     TokenController 클래스
    @GetMapping("/api/token")
    public ResponseEntity<String> getToken(HttpServletRequest request) {
        // 세션에서 액세스 토큰을 가져옵니다.
        String accessToken = (String) request.getSession().getAttribute("accessToken");

        // 액세스 토큰이 존재하는 경우, 클라이언트에게 반환합니다.
        if (accessToken != null) {
            return ResponseEntity.ok(accessToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token available");
        }
    }
}
