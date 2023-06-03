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
public class StartSubscriptionResult {
    // Data model representing a start subscription result
    private StartSubscriptionRequest request;
    private String status;
    private String workflowId;
    private Date updatedAt;

}
