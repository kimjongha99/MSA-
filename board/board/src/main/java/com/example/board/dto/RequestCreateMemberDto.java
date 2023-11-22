package com.example.board.dto;


import com.example.board.entity.Member;
import lombok.*;
import org.hibernate.boot.SessionFactoryBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestCreateMemberDto {

    private String email;
    private String password;
    private String lolNickname;
    private String userPasswordCheck;

    public Member toEntity() {
        // 암호화 비번을 저장하기 위해 필요한 암호화 라이브러리
        return Member.builder()
                .email(email)
                .password(password)
                .lolNickname(lolNickname)
                .build();
    }
}