package com.dormitory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response,
                                                               Authentication authentication) throws IOException, ServletException {

        log.info("************************ 소셜 인증 완료 ************************");


        setDefaultTargetUrl("http://localhost:3000/");

        super.onAuthenticationSuccess(request, response, authentication);

    }
}
