package com.cms.service;

import com.cms.domain.Account;
import com.cms.domain.Customer;
import com.cms.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public Optional<Account> findAccountOne(String id) {
        return null;
    }

    public void signUp(Account account) {
        validateDuplicateEmail(account.getEmail());

        accountRepository.save(account);
    }

    private void validateDuplicateEmail(String email) {
        if (accountRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }

    }
}
