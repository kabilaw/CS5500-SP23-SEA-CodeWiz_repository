package com.example.activityapp.models;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

public class ActivityErrorResponse implements ErrorResponse {
  private String message;

  public ActivityErrorResponse(int value, String message) {
    super();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public HttpStatusCode getStatusCode() {
    return null;
  }

  @Override
  public ProblemDetail getBody() {
    return null;
  }
}
