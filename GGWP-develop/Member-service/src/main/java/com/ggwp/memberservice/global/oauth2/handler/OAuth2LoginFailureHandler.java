package com.ggwp.memberservice.global.oauth2.handler;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.ggwp.memberservice.global.exception.ErrorCode.CHECK_SOCIAL_SERVER;


@Slf4j
@Component
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException {
    response.setStatus(CHECK_SOCIAL_SERVER.getHttpStatus().value());
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json;charset=UTF-8");

    String errorMessage = "{\"status\": " + CHECK_SOCIAL_SERVER.getHttpStatus().value() +
        ", \"code\": \"" + "CHECK_ID_AND_PW" +
        "\", \"message\": \"" + CHECK_SOCIAL_SERVER.getDetail() + "\"}";

    response.getWriter().write(errorMessage);
    log.info("소셜 로그인에 실패했습니다. 에러 메시지 : {}", exception.getMessage());
  }
}