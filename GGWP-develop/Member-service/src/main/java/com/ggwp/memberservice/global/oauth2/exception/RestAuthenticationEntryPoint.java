//package com.ggwp.memberservice.global.oauth2.exception;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import java.io.IOException;
//
//@Slf4j
//public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
//  @Override
//  public void commence(
//      HttpServletRequest request,
//      HttpServletResponse response,
//      AuthenticationException authException
//  ) throws IOException {
//    //authException.printStackTrace();
//    log.info("Responding with unauthorized error. Message := {}", authException.getMessage());
//    response.sendError(
//        HttpServletResponse.SC_UNAUTHORIZED,
//        authException.getLocalizedMessage()
//    );
//  }
//}