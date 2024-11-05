package com.cms.service;

import com.cms.config.FeignConfig;
import com.cms.controller.client.service.EmailSendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "spring.flyway.enabled=false")
@SpringBootTest(classes = FeignConfig.class)
class EmailSendServiceTest {

    @Autowired
    EmailSendService emailSendService;

    @Test
    void EmailTest() {
        emailSendService.sendEmail();
    }
}
