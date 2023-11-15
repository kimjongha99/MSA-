package com.ggwp.memberservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String user_id;

    @NotBlank
    private String password;

    private  String lol_nickname;
    private  String likePos;
    @Column(unique = true)
    private String uuid; //이 필드가 유일한 값임을 보장

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    private String refreshToken; // 리프레시 토큰
    @Builder
    public Member(Long id, String user_id, String password, String lol_nickname, String likePos,
                  String uuid, UserRole role, SocialType socialType, String socialId, String refreshToken) {
        this.id = id;
        this.user_id = user_id;
        this.password = password;
        this.lol_nickname = lol_nickname;
        this.likePos = likePos;
        this.uuid = uuid;
        this.role = role != null ? role : UserRole.USER;
        this.socialType = socialType;
        this.socialId = socialId;
        this.refreshToken = refreshToken;
    }

    @PrePersist
    private void ensureUuidIsGenerated() {
        if (uuid == null || uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString(); // UUID 자동 생성
        }
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = UserRole.USER;
    }


}
