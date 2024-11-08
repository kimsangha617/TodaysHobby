package com.cms.service;

import com.cms.domain.Account;
import com.cms.domain.Customer;
import com.cms.dto.AccountSignUpRequest;
import com.cms.dto.LoginRequest;
import com.cms.dto.LoginResponse;
import com.cms.repository.AccountRepository;

import com.cms.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public Optional<Account> findAccountOne(String id) {
        return null;
    }

    public void signUp(AccountSignUpRequest signUpRequest) {
        validateDuplicateEmail(signUpRequest.getEmail());
        String encryptedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Account account = Account.of(signUpRequest, encryptedPassword);

        accountRepository.save(account);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Account account = accountRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        // spring security 인증 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                account.getEmail(),
                null,
                Collections.singleton(new SimpleGrantedAuthority(account.getRole().name()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.createToken(authentication, account.getRole());

        return new LoginResponse(token);
    }


    private void validateDuplicateEmail(String email) {
        if (accountRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }

    }
}
