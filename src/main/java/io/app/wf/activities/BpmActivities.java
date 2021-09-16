package io.app.wf.activities;

import io.app.wf.model.WfStatusModel;

public interface BpmActivities {
    // created, review, outreach, approve, completed
    WfStatusModel updateStatusToGivenStatus(String status);
}
