package com.ggwp.memberservice;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class CustomErrorRes {
    private int status;
    private String errorCode;

    public static CustomErrorRes of(int status, String errorCode) {
        return new CustomErrorRes(status, errorCode);

    }
}