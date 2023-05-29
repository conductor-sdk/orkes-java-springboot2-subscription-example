package io.orkes.example.subscription.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailMetadata {
    // Data model representing email metadata
    private String userId;
    private String userEmail;
    private String emailContent;
    private String emailSubject;

}
