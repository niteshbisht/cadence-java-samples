package io.app.wf.model;

import java.util.Date;
import lombok.Data;

@Data
public class WfStatusModel {
  private String id;
  private Date statusDate;
  private String status;
}
