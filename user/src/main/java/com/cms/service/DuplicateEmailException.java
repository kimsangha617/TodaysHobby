package com.cms.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(String message) {
        super(message);
    }
}
