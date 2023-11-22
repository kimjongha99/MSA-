package com.example.board.entity;


import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Member  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private  String lolNickname;

    @Builder
    public Member(Long id, String email, String password, String lolNickname ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lolNickname = lolNickname;
    }

}