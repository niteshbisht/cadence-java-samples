package io.app.wf.config;

import static io.app.wf.common.CommonConstants.DOMAIN;
import static io.app.wf.common.CommonConstants.TASK_LIST;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import io.app.wf.activities.BpmActivitiesImpl;
import io.app.wf.workflow.BpmWorkflowImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkflowConfiguration {
  @Bean
  WorkflowClient workflowClient() {
    WorkflowClient workflowClient =
        WorkflowClient.newInstance(
            new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
            WorkflowClientOptions.newBuilder().setDomain(DOMAIN).build());
    // Get worker to poll the common task list.
    WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
    final Worker workerForCommonTaskList = factory.newWorker(TASK_LIST);
    workerForCommonTaskList.registerWorkflowImplementationTypes(BpmWorkflowImpl.class);
    BpmActivitiesImpl bpmActivities = new BpmActivitiesImpl();
    workerForCommonTaskList.registerActivitiesImplementations(bpmActivities);

    // Start all workers created by this factory.
    factory.start();
    return workflowClient;
  }
}
