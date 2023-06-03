package io.orkes.example.subscription.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import io.orkes.conductor.client.WorkflowClient;
import io.orkes.example.subscription.pojos.CancelSubscriptionRequest;
import io.orkes.example.subscription.pojos.CancelSubscriptionResult;
import io.orkes.example.subscription.pojos.CancelWebhookEventPayload;
import io.orkes.example.subscription.pojos.StartSubscriptionRequest;
import io.orkes.example.subscription.pojos.StartSubscriptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class WorkflowService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final WorkflowClient workflowClient;

    private final String conductorWebhookUrl;
    private final ObjectMapper objectMapper;

    public WorkflowService(WorkflowClient workflowClient, @Value("${conductor.subscription.updatehook.url}") String conductorWebhookUrl, ObjectMapper objectMapper) {
        this.workflowClient = workflowClient;
        this.conductorWebhookUrl = conductorWebhookUrl;
        this.objectMapper = objectMapper;
    }

    // Starts a workflow that manages the subscription by using the SDK to invoke Conductor start workflow method
    public StartSubscriptionResult triggerSubscriptionWorkflowWithTrial(StartSubscriptionRequest startSubscriptionRequest) {
        // docs-marker-start-1 (ignore this comment, used for managing conductor documentation)

        // Create a pojo for submitting the start workflow request - requires name, input data required
        StartWorkflowRequest request = new StartWorkflowRequest();
        request.setName("monthly_subscription_workflow_with_trial");

        // Input data is submitted as a key value map
        // Here we are converting our request as a key value pair
        Map<String, Object> inputData = objectMapper.convertValue(startSubscriptionRequest, new TypeReference<>() {});

        // Set the input data
        request.setInput(inputData);

        // Trigger the workflow
        String workflowId = workflowClient.startWorkflow(request);
        log.info("Workflow id: {}", workflowId);

        // docs-marker-end-1 (ignore this comment, used for managing conductor documentation)
        return StartSubscriptionResult.builder()
                .status("SUCCESS")
                .updatedAt(new Date())
                .workflowId(workflowId)
                .request(startSubscriptionRequest)
                .build();
    }

    // In this method, we will invoke an API (that is not supported on the SDK yet) to signal that the workflow
    // can be ended since the user is canceling the subscription.

    // The Webhook URL needs to be known to this application, so we should configure it ahead on the Webhooks UI on conductor
    // Its also assumed that the URL for the Conductor server is accessible from where this application is hosted.

    public CancelSubscriptionResult cancelSubscriptionWorkflowWithWebhook(CancelSubscriptionRequest cancelSubscriptionRequest) {
        // Use a Spring RestTemplate

        // Webhook URL - configure this in conductor UI and retrieve it here as a property
        String url = this.conductorWebhookUrl;

        log.info("Invoking webhook url {} for updating user cancel request for user id {}", url, cancelSubscriptionRequest.getUserId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // We should set a header that can be used by the webhook implementation to know which customer this cancellation request is for.
        // Once invoked, we know that the Conductor will complete the post cancellation steps such as sending an email to the user.
        // This key value should match what's configured in the Conductor UI for this webhook with URL - conductorWebhookUrl
        headers.add("subscriptionflow", "subscription-flow-header-unique-value");

        HttpEntity<CancelWebhookEventPayload> requestEntity = new HttpEntity<>(CancelWebhookEventPayload.builder()
                .event(cancelSubscriptionRequest)
                .build(), headers);
        // The response to webhook is Void, but status code can be checked
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

        // Return some valuable information to the caller
        return CancelSubscriptionResult.builder()
                .status("SUCCESS-" + response.getStatusCode())
                .updatedAt(new Date())
                .request(cancelSubscriptionRequest)
                .build();
    }

}
