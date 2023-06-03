package io.orkes.example.subscription.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelWebhookEventPayload {

    private CancelSubscriptionRequest event;

}
