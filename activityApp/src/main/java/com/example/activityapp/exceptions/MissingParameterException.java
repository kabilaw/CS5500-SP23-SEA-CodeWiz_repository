package com.example.activityapp.exceptions;

public class MissingParameterException extends IllegalArgumentException {
  public MissingParameterException(String message) {
    super(message);
  }
}