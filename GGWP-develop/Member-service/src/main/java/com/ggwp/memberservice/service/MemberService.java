package com.ggwp.memberservice.service;

import com.ggwp.memberservice.domain.Member;
import com.ggwp.memberservice.dto.RequestCreateMemberDto;
import com.ggwp.memberservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public void createUser(RequestCreateMemberDto requestCreateMemberDto) throws Exception {
        // dto를 entity로 변경해주는 작업이 필요함
        Member member = requestCreateMemberDto.toEntity();
        memberRepository.save(member);
    }

    //로그인


}
