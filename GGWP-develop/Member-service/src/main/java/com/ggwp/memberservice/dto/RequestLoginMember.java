package com.ggwp.memberservice.dto;

import lombok.Builder;
import lombok.Getter;
@Getter
public class RequestLoginMember {

        private String username;
        private String password;

        @Builder
        public RequestLoginMember(String username, String password){
            this.username = username;
            this.password = password;
        }

    }
