package io.app.wf.activities;

import io.app.wf.model.WfStatusModel;
import java.util.Date;
import java.util.UUID;

public class BpmActivitiesImpl implements BpmActivities {
  @Override
  public WfStatusModel updateStatusToGivenStatus(String status) {
    WfStatusModel wfStatusModel = new WfStatusModel();
    wfStatusModel.setStatus(status);
    wfStatusModel.setStatusDate(new Date());
    wfStatusModel.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    return wfStatusModel;
  }
}
