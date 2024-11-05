package com.cms.controller.client;

import com.cms.controller.client.mailgun.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {

    @PostMapping("sandboxdfcd1d0cad9e43528eafb11540ad9583.mailgun.org/messages")
    Response sendEmail(@SpringQueryMap SendMailForm form);
}
