package com.cms.controller.user;

import com.cms.annotation.CurrentUser;
import com.cms.domain.Account;
import com.cms.dto.AccountResponse;
import com.cms.dto.AccountSignUpRequest;
import com.cms.dto.LoginRequest;
import com.cms.dto.LoginResponse;
import com.cms.service.AccountDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountDetailService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Long> createUser(@Valid @RequestBody AccountSignUpRequest accountSignUpRequest) {
        accountService.signUp(accountSignUpRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = accountService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestHeader ("Refresh-Token") String refreshToken) {

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id, @CurrentUser Account account) {

        return null;
    }



}
