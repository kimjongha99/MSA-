package com.ggwp.memberservice.login.service;

import com.ggwp.memberservice.domain.Member;
import com.ggwp.memberservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private  final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저(uuid) 가 존재하지 않습니다."));
        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getUser_id())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }
}
