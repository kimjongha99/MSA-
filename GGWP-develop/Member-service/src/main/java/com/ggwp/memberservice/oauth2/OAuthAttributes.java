package com.ggwp.memberservice.oauth2;


import com.ggwp.memberservice.domain.Member;
import com.ggwp.memberservice.domain.SocialType;
import com.ggwp.memberservice.domain.UserRole;
import com.ggwp.memberservice.oauth2.userinfo.NaverOAuth2UserInfo;
import com.ggwp.memberservice.oauth2.userinfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
    private OAuth2UserInfo oauth2UserInfo; // 소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등등)

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    /**
     * SocialType에 맞는 메소드 호출하여 OAuthAttributes 객체 반환
     * 파라미터 : userNameAttributeName -> OAuth2 로그인 시 키(PK)가 되는 값 / attributes : OAuth 서비스의 유저 정보들
     * 소셜별 of 메소드(ofGoogle, ofKaKao, ofNaver)들은 각각 소셜 로그인 API에서 제공하는
     * 회원의 식별값(id), attributes, nameAttributeKey를 저장 후 build
     */
    public static OAuthAttributes of(SocialType socialType,
                                     String userNameAttributeName, Map<String, Object> attributes) {
            return ofNaver(userNameAttributeName, attributes);

    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }
    public Member toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
        return Member.builder()
                .socialType(socialType) // Social login type (NAVER, GOOGLE, etc.)
                .socialId(oauth2UserInfo.getId()) // Social ID, might be the same as user_id
                .user_id(UUID.randomUUID() + "@socialUser.com") // Assuming this is the unique identifier
                .uuid(UUID.randomUUID().toString()) // Generate a new UUID
                .role(UserRole.GUEST) // Default role, adjust as needed
                .build();
    }
}