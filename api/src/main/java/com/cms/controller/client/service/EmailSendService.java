package com.cms.controller.client.service;

import com.cms.controller.client.MailgunClient;
import com.cms.controller.client.mailgun.SendMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailSendService {
    private final MailgunClient mailgunClient;

    public void sendEmail() {

        SendMailForm form = SendMailForm.builder()
                .from("mailgun-test.my.com")
                .to("kimsangha617@gmail.com")
                .subject("Test email from Mailgun")
                .text("my Text um hahaha")
                .build();
        mailgunClient.sendEmail(form);
    }
}
