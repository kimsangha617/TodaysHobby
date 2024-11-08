package com.cms.controller.user;

import com.cms.dto.AccountSignUpRequest;
import com.cms.dto.LoginRequest;
import com.cms.dto.LoginResponse;
import com.cms.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Long> createUser(@Valid @RequestBody AccountSignUpRequest accountSignUpRequest) {
        accountService.signUp(accountSignUpRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse loginResponse = accountService.login(request);
        return ResponseEntity.ok(loginResponse);
    }




}
