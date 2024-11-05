package com.cms.controller.user.dto;

import com.cms.domain.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AccountDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AccountSignupFormDto {
//        private Long sellerId;
        @NotBlank
        @Size(min = 2, max = 10)
        private String userName;

        @NotBlank
        @Size(min = 11, max = 50)
        private String email;

        @NotBlank
        @Size(min = 8, max = 20)
        private String password;

        @NotBlank
        @Size(min = 11, max = 11)
        private String phoneNumber;

        public static Account toEntity(AccountSignupFormDto request) {
            return Account.builder()
                    .accountName(request.userName)
                    .email(request.email)
                    .phoneNumber(request.phoneNumber)
                    .password(request.password)
                    .build();
        }

//        public static Product toEntity(AccountDto.AccountSaveRequest request) {
//            return Product.builder()
//                    .sellerId(request.sellerId)
//                    .koreanName(request.koreanName)
//                    .englishName(request.englishName)
//                    .description(request.description)
//                    .thumbnailImagePath(request.thumbnailImagePath)
//                    .build();
//        }
    }


}
