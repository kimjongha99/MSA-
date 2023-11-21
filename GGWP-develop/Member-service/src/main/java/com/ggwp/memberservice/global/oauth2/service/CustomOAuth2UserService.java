package com.ggwp.memberservice.global.oauth2.service;


import com.ggwp.memberservice.domain.Member;

import com.ggwp.memberservice.global.entity.ProviderType;
import com.ggwp.memberservice.global.exception.CustomException;
import com.ggwp.memberservice.global.exception.ErrorCode;
import com.ggwp.memberservice.global.oauth2.CustomOAuth2User;
import com.ggwp.memberservice.global.oauth2.dto.OAuthAttributes;
import com.ggwp.memberservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final MemberRepository memberRepository;

  private static final String NAVER = "naver";
  private static final String KAKAO = "kakao";
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info("CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");

    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    ProviderType providerType = getProviderType(registrationId);
    String userNameAttributeName = userRequest.getClientRegistration()
        .getProviderDetails().getUserInfoEndpoint()
        .getUserNameAttributeName(); // OAuth2 로그인 시 키(PK)가 되는 값
    Map<String, Object> attributes = oAuth2User.getAttributes(); // 소셜 로그인에서 API가 제공하는 userInfo의 Json 값(유저 정보들)

    //ProviderType에 따라 유저 정보를 통해 OAuthAttributes 객체 생성
    OAuthAttributes extractAttributes = OAuthAttributes.of(providerType, userNameAttributeName,
        attributes);

    Member createdUser = getMember(extractAttributes, providerType); // getUser() 메소드로 User 객체 생성 후 반환

    // DefaultOAuth2User를 구현한 CustomOAuth2User 객체를 생성해서 반환
    return new CustomOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().getKey())),
        attributes,
        extractAttributes.getNameAttributeKey(),
        createdUser.getEmail(),
        createdUser.getRole()
    );
  }

  private ProviderType getProviderType(String registrationId) {
    if (NAVER.equals(registrationId)) {
      return ProviderType.NAVER;
    } else if (KAKAO.equals(registrationId)) {
      return ProviderType.KAKAO;
    }
    return ProviderType.GOOGLE;
  }

  private Member getMember(OAuthAttributes attributes, ProviderType providerType) {
    Member findMember = memberRepository.findByProviderTypeAndSocialId(providerType,
        attributes.getOauth2UserInfo().getSocialId()).orElse(null);

    if (findMember == null) {
      return saveMember(attributes, providerType);
    }
    return findMember;
  }

  private Member saveMember(OAuthAttributes attributes, ProviderType providerType) {
    if(memberRepository.existsByEmail( attributes.getOauth2UserInfo().getEmail())){
      //TODO: 이미 등록되어있는 이메일입니다. 뜨고 이후에 처리 어떻게 할것인지.
      throw new CustomException(ErrorCode.ALREADY_EXIST_EMAIL);
    }
    Member createdMember = attributes.toEntity(providerType, attributes.getOauth2UserInfo());
    return memberRepository.save(createdMember);
  }
}