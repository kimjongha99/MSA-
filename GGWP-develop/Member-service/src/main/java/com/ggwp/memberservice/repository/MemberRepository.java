package com.ggwp.memberservice.repository;

import com.ggwp.memberservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
