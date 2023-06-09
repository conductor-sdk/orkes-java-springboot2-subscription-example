package io.orkes.example.subscription.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartSubscriptionRequest {

    // Data model representing a start subscription request

    private String userId;
    private String userEmail;
    private String billingAmount;
    private String billingPeriods;

}
