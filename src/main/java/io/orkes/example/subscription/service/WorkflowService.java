package io.orkes.example.subscription.service;

import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import io.orkes.conductor.client.WorkflowClient;
import io.orkes.example.subscription.pojos.CancelSubscriptionRequest;
import io.orkes.example.subscription.pojos.CancelSubscriptionResult;
import io.orkes.example.subscription.pojos.StartSubscriptionRequest;
import io.orkes.example.subscription.pojos.StartSubscriptionResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class WorkflowService {

    private final WorkflowClient workflowClient;
    private final RestTemplate restTemplate = new RestTemplate();
    s
    // Starts a workflow that manages the subscription by using the SDK to invoke Conductor start workflow method
    public StartSubscriptionResult triggerSubscriptionWorkflowWithTrial(StartSubscriptionRequest startSubscriptionRequest) {
        // docs-marker-start-1

        StartWorkflowRequest request = new StartWorkflowRequest();
        request.setName("monthly_subscription_workflow_with_trial");
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("userId", startSubscriptionRequest.getUserId());
        request.setInput(inputData);

        String workflowId = workflowClient.startWorkflow(request);
        log.info("Workflow id: {}", workflowId);

        // docs-marker-end-1
        return StartSubscriptionResult.builder()
                .status("SUCCESS")
                .updatedAt(Instant.now())
                .request(startSubscriptionRequest)
                .build();
    }

    // In this method, we will invoke an API (that is not supported on the SDK yet) to signal that the workflow
    // can be ended since the user is canceling the subscription.

    // The Webhook URL needs to be known to this application, so we should configure it ahead on the Webhooks UI on conductor
    // Its also assumed that the URL for the Conductor server is accessible from where this application is hosted.

    public CancelSubscriptionResult cancelSubscriptionWorkflowWithWebhook(CancelSubscriptionRequest cancelSubscriptionRequest) {
        // Use a Spring RestTemplate

        // We should set a header that can be used by the webhook implementation to know which customer this cancellation request is for.
        // Once invoked, we know that the Conductor will complete the post cancellation steps such as sending an email to the user.

        // TODO Invoke Webhook
        return CancelSubscriptionResult.builder()
                .status("SUCCESS")
                .updatedAt(Instant.now())
                .request(cancelSubscriptionRequest)
                .build();
    }

}
