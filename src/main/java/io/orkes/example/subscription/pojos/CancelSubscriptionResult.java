package io.orkes.example.subscription.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelSubscriptionResult {
    // Data model representing a cancel subscription result
    private CancelSubscriptionRequest request;
    private String status;
    private Date updatedAt;
}
