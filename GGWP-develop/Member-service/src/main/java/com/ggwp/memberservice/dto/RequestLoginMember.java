package com.ggwp.memberservice.dto;

import lombok.Builder;
import lombok.Getter;
@Getter
public class RequestLoginMember {

        private String user_id;
        private String password;

        @Builder
        public RequestLoginMember(String user_id, String password){
            this.user_id = user_id;
            this.password = password;
        }

    }
