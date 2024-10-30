package com.cms.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final T data;
    private final String message;
    private final Integer code;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(data, "success", 200);
    }
}
