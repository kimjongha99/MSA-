package com.ggwp.memberservice.dto;

import com.ggwp.memberservice.domain.Member;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder @ToString
public class RequestCreateMemberDto {

    private String user_id;     // Assuming you meant 'userId' instead of 'user_id'
    private String password;
    private String lol_nickname;


    public Member toEntity() {
        // 암호화 비번을 저장하기 위해 필요한 암호화 라이브러리
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return Member.builder()
                .user_id(user_id) // user_id를 username 필드에 매핑
                .password(bCryptPasswordEncoder.encode(password)) // 비밀번호 암호화
                .lol_nickname(lol_nickname) // lolNickname을 lol_nickname 필드에 매핑
                .build();
    }

}
