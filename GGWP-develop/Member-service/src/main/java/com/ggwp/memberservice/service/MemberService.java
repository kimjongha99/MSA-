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
        if(memberRepository.findByUser_id(requestCreateMemberDto.getUser_id()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다");
        }
        if(memberRepository.findByLol_nickname(requestCreateMemberDto.getLol_nickname()).isPresent()){
            throw new Exception("이미 존재하는 롤닉네임입니다"); // 추후에 rso 적용시 리펙터링
        }
        // dto를 entity로 변경해주는 작업이 필요함
        Member member = requestCreateMemberDto.toEntity();
        memberRepository.save(member);
    }

    //로그인


}
