package com.cms.service;

import com.cms.domain.Account;
import com.cms.repository.AccountRepository;
import com.cms.security.AccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        try {
            Account account = accountRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UsernameNotFoundException(id + "계정은 존재하지 않습니다."));
            return AccountDetails.builder()
                    .id(account.getId())
                    .build();
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException(id + "계정은 존재하지 않습니다.");
        }
    }
}
