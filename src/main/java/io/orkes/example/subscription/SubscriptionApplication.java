package io.orkes.example.subscription;

import io.orkes.conductor.client.ApiClient;
import io.orkes.conductor.client.TaskClient;
import io.orkes.conductor.client.WorkflowClient;
import io.orkes.conductor.client.http.OrkesTaskClient;
import io.orkes.conductor.client.http.OrkesWorkflowClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@AllArgsConstructor
@SpringBootApplication(scanBasePackages = {"com.netflix.conductor", "io.orkes.example"})
public class SubscriptionApplication {

	private final Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionApplication.class, args);
	}

	@Bean
	public ApiClient apiClient() {
		String key = env.getProperty("orkes.access.key");
		String secret = env.getProperty("orkes.access.secret");
		String conductorServer = env.getProperty("orkes.conductor.server.url");
		return new ApiClient(conductorServer, key, secret);
	}

	@Bean
	public TaskClient getTaskClient(ApiClient apiClient) {
		return new OrkesTaskClient(apiClient);
	}

	@Bean
	public WorkflowClient getWorkflowClient(ApiClient apiClient) {
		return new OrkesWorkflowClient(apiClient);
	}

}
