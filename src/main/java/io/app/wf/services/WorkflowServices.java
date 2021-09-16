package io.app.wf.services;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import io.app.wf.model.WfStatusModel;
import io.app.wf.workflow.BpmWorkflowImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkflowServices {

  private final WorkflowClient workflowClient;

  public String createTransaction() {
    WorkflowExecution start = WorkflowClient.start(BpmWorkflowImpl::createTransaction, null);
    return start.getWorkflowId();
  }

  public WfStatusModel updateStatus(String status, String workflowId) {
    BpmWorkflowImpl bpmWorkflow = workflowClient.newWorkflowStub(BpmWorkflowImpl.class, workflowId);
    bpmWorkflow.updateState(status, false);
    return bpmWorkflow.getStatus();
  }
}
