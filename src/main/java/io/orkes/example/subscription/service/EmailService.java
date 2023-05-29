package io.orkes.example.subscription.service;

import io.orkes.example.subscription.pojos.EmailMetadata;
import io.orkes.example.subscription.pojos.SendEmailResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class EmailService {

    public SendEmailResult sendEmail(EmailMetadata emailMetadata) {
        log.info("Sending email to user {}", emailMetadata.getUserId());
        return SendEmailResult.builder()
                .emailMetadata(emailMetadata)
                .status("SUCCESS")
                .timeSent(Instant.now())
                .build();
    }

}
