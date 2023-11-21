package com.ggwp.memberservice.global.oauth2.info;


import com.ggwp.memberservice.global.oauth2.OAuth2UserInfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

  public NaverOAuth2UserInfo(Map<String, Object> attributes) {
    super((Map<String, Object>) attributes.get("response"));
  }

  @Override
  public String getSocialId() {
    return (String) attributes.get("id");
  }

  @Override
  public String getName() {
    return (String) attributes.get("name");
  }

  @Override
  public String getEmail() {
    return (String) attributes.get("email");
  }
}