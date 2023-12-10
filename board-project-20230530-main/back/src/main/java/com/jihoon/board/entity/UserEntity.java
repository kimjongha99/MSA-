package com.jihoon.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jihoon.board.dto.request.auth.SignUpRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {
  @Id
  private String email;
  private String password;
  private String nickname;
  private String telNumber;
  private String address;
  private String addressDetail;
  private String profileImageUrl;

  public UserEntity(SignUpRequestDto dto) {
    this.email = dto.getEmail();
    this.password = dto.getPassword();
    this.nickname = dto.getNickname();
    this.telNumber = dto.getTelNumber();
    this.address = dto.getAddress();
    this.addressDetail = dto.getAddressDetail();
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }
}
