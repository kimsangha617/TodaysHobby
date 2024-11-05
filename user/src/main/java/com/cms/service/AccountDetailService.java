package com.cms.service;

import com.cms.domain.Account;
import com.cms.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Account account = accountRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UsernameNotFoundException(id + "계정은 존재하지 않습니다."));

        return User.builder()
                .username(account.getAccountName())
                .password(account.getPassword())
                .roles(account.getRole().name())
                .build();
    }
}
