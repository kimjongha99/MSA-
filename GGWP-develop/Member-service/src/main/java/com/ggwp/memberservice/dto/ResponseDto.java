package com.ggwp.memberservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
    private String response;
    private String message;

    public ResponseDto(String response, String message) {
        this.response = response;
        this.message = message;
    }
}