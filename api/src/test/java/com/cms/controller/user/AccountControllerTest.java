package com.cms.controller.user;

import com.cms.domain.Account;
import com.cms.dto.AccountSignUpRequest;
import com.cms.dto.LoginRequest;
import com.cms.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = "spring.flyway.enabled=false")
@AutoConfigureMockMvc
@SpringBootTest
class AccountControllerTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @DisplayName("회원가입_성공")
    @Test
    void signUpSuccess() throws Exception {

        AccountSignUpRequest request = AccountSignUpRequest.builder()
                .accountName("test")
                .email("test@gmail.com")
                .phoneNumber("01012345678")
                .password("12345678")
                .build();

        mockMvc.perform(post("/api/v1/account/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("존재하는_이메일_회원가입_실패")
    @Test
    void signUpFailExistsEmail() throws Exception {
        AccountSignUpRequest request = AccountSignUpRequest.builder()
                .accountName("test")
                .email("test@gmail.com")
                .phoneNumber("01012345678")
                .password("12345678")
                .build();

        mockMvc.perform(post("/api/v1/account/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("로그인_성공_JWT토큰_발급")
    @Test
    void successLoginAndJwtBeIssued() throws Exception {
        generateAccount(1L);

        LoginRequest request = LoginRequest.builder()
                .email("test1@gmail.com")
                .password("12345678")
                .build();

        mockMvc.perform(post("/api/v1/account/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andDo(print());

    }

    @DisplayName("존재하는_회원_조회")
    @Test
    void getAccount() {
        Account account = generateAccount(1L);

//        mockMvc.perform(get("/api/v1/account/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print())
//                ;

    }

    void loginGetToken() {

    }


    @DisplayName("회원가입_실패")
    @Test
    void signUpFail() {

    }

    private Account generateAccount(Long index) {
        String encodedPassword = passwordEncoder.encode("12345678");
        Account account = Account.builder()
                .email("test" + index + "@gmail.com")
                .password(encodedPassword)
                .phoneNumber("01012345678")
                .accountName("test" + index)
                .build();
        return this.accountRepository.save(account);
    }

}