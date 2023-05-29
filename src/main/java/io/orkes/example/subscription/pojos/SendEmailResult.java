package io.orkes.example.subscription.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailResult {
    // Data model representing result of the email sending attempt - including the request

    private EmailMetadata emailMetadata;
    private String status;
    private Instant timeSent;

}
