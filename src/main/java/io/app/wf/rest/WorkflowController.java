package io.app.wf.rest;

import com.fasterxml.jackson.databind.JsonNode;
import io.app.wf.model.WfStatusModel;
import io.app.wf.services.WorkflowServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WorkflowController {
  private WorkflowServices workflowServices;

  @GetMapping("/create")
  public ResponseEntity<String> createTrasaction(@RequestBody String taskMessage) {
    return new ResponseEntity<>(workflowServices.createTransaction(taskMessage), HttpStatus.CREATED);
  }

  @PostMapping("/update-status")
  public ResponseEntity<WfStatusModel> updateStatus(@RequestBody JsonNode payload) {
    return new ResponseEntity<>(
        workflowServices.updateStatus(
            payload.get("status").asText(), payload.get("workflowId").asText()),
        HttpStatus.CREATED);
  }
}
