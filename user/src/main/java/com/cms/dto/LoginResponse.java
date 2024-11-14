package com.cms.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
public class LoginResponse {
    private String accessToken;
    private String email;
    private String role;
}
