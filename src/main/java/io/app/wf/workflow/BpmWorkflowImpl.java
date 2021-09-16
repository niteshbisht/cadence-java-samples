package io.app.wf.workflow;

import com.uber.cadence.workflow.Workflow;
import io.app.wf.activities.BpmActivitiesImpl;
import io.app.wf.model.WfStatusModel;

public class BpmWorkflowImpl implements BpmWorkflow{
    private String status;
    private boolean initialized;
    private WfStatusModel wfStatusModel;
    private final BpmActivitiesImpl bpmActivities = Workflow.newActivityStub(BpmActivitiesImpl.class);
    // created, review, outreach, approve, completed
    @Override
    public void createTransaction() {
        updateState("created", true);
        while (true) {
            if("completed".equalsIgnoreCase(status)) {
                break;
            }
            Workflow.sleep(1000);
        }
    }

    @Override
    public WfStatusModel updateState(String status, boolean isFromCreate) {
        if("created".equalsIgnoreCase(status) && isFromCreate ) {
            if(!initialized) {
                initialized = true;
                this.wfStatusModel = bpmActivities.updateStatusToGivenStatus(status);
                this.status = this.wfStatusModel.getStatus();
            }
        } else {
            this.wfStatusModel = bpmActivities.updateStatusToGivenStatus(status);
            this.status = this.wfStatusModel.getStatus();
        }
        return this.wfStatusModel;
    }
}
