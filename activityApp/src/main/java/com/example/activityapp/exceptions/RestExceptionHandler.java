package com.example.activityapp.exceptions;

import com.example.activityapp.models.ActivityErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(MissingParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ActivityErrorResponse handleMissingParameterException(MissingParameterException ex) {
    return new ActivityErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  @ExceptionHandler(InvalidParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ActivityErrorResponse handleInvalidParameterException(InvalidParameterException ex) {
    return new ActivityErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
  }

  @ExceptionHandler(ActivityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ActivityErrorResponse handleActivityNotFoundException(ActivityNotFoundException ex) {
    return new ActivityErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

}