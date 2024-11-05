package com.cms.controller.user;

import com.cms.controller.user.dto.AccountDto;
import com.cms.domain.Account;
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
@RequestMapping("/api/v1/user")
public class UserController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Long> createUser(@Valid @RequestBody AccountDto.AccountSignupFormDto accountSaveRequest) {
        Account newAccountInstance = AccountDto.AccountSignupFormDto.toEntity(accountSaveRequest);
        accountService.signUp(newAccountInstance);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }




}
