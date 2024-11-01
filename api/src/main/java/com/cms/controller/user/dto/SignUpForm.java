package com.cms.controller.user.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpForm {
    private String email;
    private String name;
    private String password;
    private LocalDate birth;
    private String phoneNumber;


}
