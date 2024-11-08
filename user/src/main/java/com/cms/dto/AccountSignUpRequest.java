package com.cms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AccountSignUpRequest {
    @NotBlank
    @Size(min = 2, max = 10)
    private String accountName;

    @NotBlank
    @Size(min = 11, max = 50)
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;

}
