package io.app.wf.workflow;

import com.uber.cadence.workflow.QueryMethod;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import io.app.wf.common.CommonConstants;
import io.app.wf.model.WfStatusModel;

public interface BpmWorkflow {
  @WorkflowMethod(taskList = CommonConstants.TASK_LIST)
  public void createTransaction();

  @SignalMethod
  public void updateState(String status, boolean isFromCreate);

  @QueryMethod
  public WfStatusModel getStatus();
}
