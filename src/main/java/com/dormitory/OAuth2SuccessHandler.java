package com.dormitory;

import com.dormitory.util.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    private final OAuth2AuthorizedClientService authorizedClientService;
//    private final ObjectMapper objectMapper;
////
////    private final MemberService service;
//    private final JWTUtil jwtUtil;
//
////    public OAuth2SuccessHandler(OAuth2AuthorizedClientService authorizedClientService, ObjectMapper objectMapper, JWTUtil jwtUtil) {
////        this.authorizedClientService = authorizedClientService;
////        this.objectMapper = objectMapper;
////
////
////        this.jwtUtil = jwtUtil;
////    }
//    @Override
//    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response,
//                                                               Authentication authentication) throws IOException, ServletException {
//
//        log.info("************************ 소셜 인증 완료 ************************");
//
//
//        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//        String registrationId = oauthToken.getAuthorizedClientRegistrationId();
//        System.out.println("!!!!!!!!!!!!!!!" +registrationId );
//
//
//        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
//                registrationId, oauthToken.getName());
//
//        // 액세스 토큰을 추출합니다.
//        String accessToken = authorizedClient.getAccessToken().getTokenValue();
//
//
//
//        // 응답을 JSON 형식으로 설정합니다.
//        response.setContentType("application/json;charset=UTF-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//
//
//        // 쿠키를 생성하고 응답에 추가합니다.
//        Cookie cookie = new Cookie("access_token", accessToken);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(7 * 24 * 60 * 60); // 7일간 유효
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
//
//
////        service.setToken(accessToken);
//
//        // 토큰을 포함한 응답 바디를 작성합니다.
//        Map<String, String> tokenInfo = new HashMap<>();
//        tokenInfo.put("access_token", accessToken);
//
//        // JSON으로 변환하여 응답 바디에 쓰기
//        response.getWriter().write(objectMapper.writeValueAsString(tokenInfo));
//
//        response.sendRedirect("http://localhost:3000/redirect?access_token=" + accessToken);
//
//    }


    @Override
    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response,
                                         Authentication authentication) throws IOException, ServletException, IOException {

        log.info("************************ 소셜 인증 완료 ************************");

        System.out.println("소셜!!!!인증!!!!!" + authentication.getPrincipal());
        Object principal = authentication.getPrincipal();
        // Principal이 OAuth2User를 구현한 객체인지 확인합니다.
        if (principal instanceof OAuth2User) {
            // OAuth2User를 구현한 객체로 형변환하여 사용자 정보에 접근합니다.
            OAuth2User oAuth2User = (OAuth2User) principal;

            // 원하는 OAuth2 사용자 정보에 접근할 수 있습니다.
//            String userid = oAuth2User.getAttribute("email");
            // 사용자 정보를 활용한 로직을 수행합니다.
//            System.out.println("UserID: " + userid);
//            System.out.println("카카오UserID: " + oAuth2User.getAttribute("kakao_account"));
//            Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttribute("kakao_account");
//            String email = (String) kakaoAccount.get("email");
//            System.out.println("카카오 아이디 제발 : " + email);

            String provider = "";
            String userid = "";

// 구글 로그인
            if (oAuth2User.getAttribute("email") != null) {
                provider = "google";
                userid = oAuth2User.getAttribute("email");
            }
// 카카오 로그인
            else if (oAuth2User.getAttribute("kakao_account") != null) {
                provider = "kakao";
                Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttribute("kakao_account");
                userid = (String) kakaoAccount.get("email");
            }
// 네이버 로그인
            else if (oAuth2User.getAttribute("response") != null) {
                provider = "naver";
                Map<String, Object> naverAccount = (Map<String, Object>) oAuth2User.getAttribute("response");
                userid = (String) naverAccount.get("email");
            }


            JWTUtil jwtUtil = new JWTUtil();
            Map<String,Object> data = new HashMap<>();
            data.put("userid", userid);

            //access token & refresh token 생성
            String accessToken = jwtUtil.generateToken(data, 7);
//            String refreshToken = jwtUtil.generateToken(data, 5);

//            Cookie cookie = new Cookie("accessToken", accessToken);
//            response.addCookie(cookie);

            Cookie cookie = new Cookie("accessToken", accessToken);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7일간 유효
            response.addCookie(cookie);

            Cookie cookie2 = new Cookie("userid", userid);
            cookie2.setPath("/");
            cookie2.setMaxAge(7 * 24 * 60 * 60); // 7일간 유효
            System.out.println("usrid : " + userid);
            response.addCookie(cookie2);

            System.out.println("jwt토큰 : " + accessToken);

//            cookie.setHttpOnly(true);

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("accessToken".equals(c.getName())) {
                        System.out.println("쿠키 값: " + c.getValue());
                        break;
                    }
                }
            }


        }

        setDefaultTargetUrl("http://localhost:3000/");

        super.onAuthenticationSuccess(request, response, authentication);

    }


}
