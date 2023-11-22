package com.ggwp.memberservice.service;

import com.ggwp.memberservice.domain.Member;
import com.ggwp.memberservice.dto.RequestCreateMemberDto;
import com.ggwp.memberservice.global.exception.CustomException;
import com.ggwp.memberservice.global.util.HeaderUtil;
import com.ggwp.memberservice.repository.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import static com.ggwp.memberservice.global.exception.ErrorCode.ALREADY_EXIST_EMAIL;
import static com.ggwp.memberservice.global.exception.ErrorCode.ALREADY_EXIST_NICKNAME;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final MemberRepository memberRepository;


    // 회원가입
    public void createUser(RequestCreateMemberDto requestCreateMemberDto) {

        if(memberRepository.existsByEmail(requestCreateMemberDto.getEmail())){
            throw new CustomException(ALREADY_EXIST_EMAIL);
        }else if(memberRepository.existsByLolNickname(requestCreateMemberDto.getLolNickname())){
            throw  new CustomException(ALREADY_EXIST_NICKNAME);
        }

        Member member = requestCreateMemberDto.toEntity();
        memberRepository.save(member);
    }
    //로그아웃
    @Transactional
    public void logout(HttpServletRequest request, Principal principal) {
        //헤더에서 토큰 가져와서 Bearer부분 제거후 사용
        String token = HeaderUtil.getAccessToken(request);


    }

}
