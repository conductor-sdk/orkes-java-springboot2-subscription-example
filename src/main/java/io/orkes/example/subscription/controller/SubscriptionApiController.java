package io.orkes.example.subscription.controller;

import io.orkes.example.subscription.pojos.*;
import io.orkes.example.subscription.service.WorkflowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class SubscriptionApiController {

    // This controller that exposes the APIs relies on this service class and here the service class is responsible for just interacting with
    // Conductor workflow system
    private final WorkflowService workflowService;

    // docs-marker-start-1
    // Starts a workflow that manages the subscription
    @PostMapping(value = "/startSubscription", produces = "application/json")
    public ResponseEntity<StartSubscriptionResult> startSubscription(@RequestBody StartSubscriptionRequest startSubscriptionRequest) {
        log.info("Starting subscription: {}", startSubscriptionRequest);
        return ResponseEntity.ok(workflowService.triggerSubscriptionWorkflowWithTrial(startSubscriptionRequest));
    }
    // docs-marker-end-1


    // docs-marker-start-2

    @PostMapping(value = "/cancelSubscription", produces = "application/json")
    public ResponseEntity<CancelSubscriptionResult> cancelSubscription(@RequestBody CancelSubscriptionRequest cancelSubscriptionRequest) {
        log.info("Canceling subscription: {}", cancelSubscriptionRequest);
        return ResponseEntity.ok(workflowService.cancelSubscriptionWorkflowWithWebhook(cancelSubscriptionRequest));
    }

    // docs-marker-end-2

    // docs-marker-start-3

    @PostMapping(value = "/updateSubscription", produces = "application/json")
    public ResponseEntity<UpdateSubscriptionResult> cancelSubscription(@RequestBody UpdateSubscriptionRequest updateSubscriptionRequest) {
        log.info("Updating subscription details: {}", updateSubscriptionRequest);
        return ResponseEntity.ok(workflowService.updateSubscriptionWorkflowVariables(updateSubscriptionRequest));
    }

    // docs-marker-end-3
}
