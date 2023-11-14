package com.ggwp.memberservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @Column(unique = true)
    private String uuid; // 이 필드가 유일한 값임을 보장

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;

    @Builder
    public Member(Long id, String user_id, String password, String lol_nickname, String uuid, UserRole role) {
        this.id = id;
        this.user_id = user_id;
        this.password = password;
        this.lol_nickname = lol_nickname;
        this.uuid = uuid;
        this.role = role != null ? role : UserRole.ROLE_USER;
    }

    @PrePersist
    private void ensureUuidIsGenerated() {
        if (uuid == null || uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString(); // UUID 자동 생성
        }
    }
}
