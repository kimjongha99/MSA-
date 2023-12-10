package com.jihoon.board.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jihoon.board.dto.request.user.PatchUserNicknameRequestDto;
import com.jihoon.board.dto.request.user.PatchUserProfileRequestDto;
import com.jihoon.board.dto.response.user.GetSignInUserResponseDto;
import com.jihoon.board.dto.response.user.GetUserResponseDto;
import com.jihoon.board.dto.response.user.PatchUserNicknameResponseDto;
import com.jihoon.board.dto.response.user.PatchUserProfileResponseDto;
import com.jihoon.board.service.UserService;

import lombok.RequiredArgsConstructor;

// controller: 유저 컨트롤러 //
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  
  // API : 유저 정보 불러오기 메서드 //
  @GetMapping("/{email}")
  public ResponseEntity<? super GetUserResponseDto> getUser(
    @PathVariable(value="email", required=true) String email
  ) {
    ResponseEntity<? super GetUserResponseDto> response = userService.getUser(email);
    return response;
  }

  // API : 로그인 유저 정보 불러오기 메서드 //
  @GetMapping("")
  public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
    @AuthenticationPrincipal String email
  ) {
    ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(email);
    return response;
  }

  // API : 유저 닉네임 수정 메서드 //
  @PatchMapping("/nickname")
  public ResponseEntity<? super PatchUserNicknameResponseDto> patchUserNickname(
    @AuthenticationPrincipal String email,
    @RequestBody @Valid PatchUserNicknameRequestDto requestBody
  ) {
    ResponseEntity<? super PatchUserNicknameResponseDto> response = userService.patchUserNickname(email, requestBody);
    return response;
  }

  // API : 유저 프로필 이미지 수정 메서드 //
  @PatchMapping("/profile")
  public ResponseEntity<? super PatchUserProfileResponseDto> patchUserProfile(
    @AuthenticationPrincipal String email,
    @RequestBody @Valid PatchUserProfileRequestDto requestBody
  ) {
    ResponseEntity<? super PatchUserProfileResponseDto> response = userService.patchUserProfile(email, requestBody);
    return response;
  }

}
