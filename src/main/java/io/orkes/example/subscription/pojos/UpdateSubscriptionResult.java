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
public class UpdateSubscriptionResult {
    // Data model representing a cancel subscription result
    private UpdateSubscriptionRequest request;
    private String status;
    private Date updatedAt;
}
