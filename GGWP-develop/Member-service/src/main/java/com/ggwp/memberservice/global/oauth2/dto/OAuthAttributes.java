  package com.ggwp.memberservice.global.oauth2.dto;


  import com.ggwp.memberservice.domain.Member;
  import com.ggwp.memberservice.global.entity.ProviderType;
  import com.ggwp.memberservice.global.entity.RoleType;
  import com.ggwp.memberservice.global.oauth2.OAuth2UserInfo;
  import com.ggwp.memberservice.global.oauth2.info.GoogleOAuth2UserInfo;
  import com.ggwp.memberservice.global.oauth2.info.KakaoOAuth2UserInfo;
  import com.ggwp.memberservice.global.oauth2.info.NaverOAuth2UserInfo;
  import lombok.Builder;
  import lombok.Getter;

  import java.util.Map;

  @Getter
  public class OAuthAttributes {

    private final String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
    private final OAuth2UserInfo oauth2UserInfo; // 소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등등)

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
      this.nameAttributeKey = nameAttributeKey;
      this.oauth2UserInfo = oauth2UserInfo;
    }

    public static OAuthAttributes of(ProviderType socialType,
                                     String userNameAttributeName, Map<String, Object> attributes) {

      if (socialType == ProviderType.NAVER) {
        return ofNaver(userNameAttributeName, attributes);
      }
      if (socialType == ProviderType.KAKAO) {
        return ofKakao(userNameAttributeName, attributes);
      }
      return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName,
        Map<String, Object> attributes) {
      return OAuthAttributes.builder()
          .nameAttributeKey(userNameAttributeName)
          .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
          .build();
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName,
        Map<String, Object> attributes) {
      return OAuthAttributes.builder()
          .nameAttributeKey(userNameAttributeName)
          .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
          .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName,
        Map<String, Object> attributes) {
      return OAuthAttributes.builder()
          .nameAttributeKey(userNameAttributeName)
          .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
          .build();
    }

    public Member toEntity(ProviderType providerType, OAuth2UserInfo oauth2UserInfo) {
      return new Member(
              oauth2UserInfo.getName(), // Assuming getName() gives the nickname
              oauth2UserInfo.getEmail(),
              oauth2UserInfo.getSocialId(), // Unique ID from the OAuth2 provider
              providerType,
              RoleType.USER // Assuming a default role, change as needed
      );
    }
  }

