package io.orkes.example.subscription.workers;

import com.netflix.conductor.sdk.workflow.task.WorkerTask;
import io.orkes.example.subscription.pojos.EmailMetadata;
import io.orkes.example.subscription.pojos.SendEmailResult;
import io.orkes.example.subscription.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class ConductorWorkers {

    // Service to trigger an actual email sending
    private final EmailService emailService;

    // docs-marker-start-1

    /**
     * Note: Using this setting, up to 5 tasks will run in parallel, with tasks being polled every 200ms
     */
    @WorkerTask(value = "send-email", threadCount = 5, pollingInterval = 200)
    public SendEmailResult sendEmail(EmailMetadata emailMetadata) {
        return emailService.sendEmail(emailMetadata);
    }

    // docs-marker-end-1


}
