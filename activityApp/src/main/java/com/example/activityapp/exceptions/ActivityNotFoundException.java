package com.example.activityapp.exceptions;

public class ActivityNotFoundException extends RuntimeException {
  public ActivityNotFoundException(String message) {
    super(message);
  }
}