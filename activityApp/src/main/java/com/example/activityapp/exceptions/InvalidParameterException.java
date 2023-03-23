package com.example.activityapp.exceptions;

public class InvalidParameterException extends IllegalArgumentException {
  public InvalidParameterException(String message) {
    super(message);
  }
}