package io.orkes.example.subscription.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubscriptionRequest {
    // Data model representing a update subscription request
    private String userId;
    private String billingAmount;
    private String billingPeriods;

}
