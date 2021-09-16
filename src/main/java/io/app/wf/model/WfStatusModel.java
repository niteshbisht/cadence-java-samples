package io.app.wf.model;

import lombok.Data;

import java.util.Date;

@Data
public class WfStatusModel {
    private String id;
    private Date statusDate;
    private String status;
}
