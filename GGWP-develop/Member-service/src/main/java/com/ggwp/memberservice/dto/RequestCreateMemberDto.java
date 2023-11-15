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

    private String user_id;
    private String password;
    private String lol_nickname;
    private String likePos;

    public Member toEntity() {
        // 암호화 비번을 저장하기 위해 필요한 암호화 라이브러리
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return Member.builder()
                .user_id(user_id)
                .password(bCryptPasswordEncoder.encode(password))
                .lol_nickname(lol_nickname)
                .likePos(likePos)
                .build();
    }

}
