package com.example.activityapp.models;

import java.util.List;

public class Activity {
  private String activity;
  private String group;
  private Boolean manual;
  private String startTime;
  private String endTime;
  private Double duration;
  private Double distance;
  private Integer steps;
  private Integer calories;
  private List<Object> trackPoints;

  public Activity() {
  }

  public Activity(String activity, String group, Boolean manual, String startTime, String endTime,
      Double duration, Double distance, Integer steps, Integer calories, List<Object> trackPoints) {
    this.activity = activity;
    this.group = group;
    this.manual = manual;
    this.startTime = startTime;
    this.endTime = endTime;
    this.duration = duration;
    this.distance = distance;
    this.steps = steps;
    this.calories = calories;
    this.trackPoints = trackPoints;
  }

  public String getActivity() {
    return activity;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public Boolean getManual() {
    return manual;
  }

  public void setManual(Boolean manual) {
    this.manual = manual;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Double getDuration() {
    return duration;
  }

  public void setDuration(Double duration) {
    this.duration = duration;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public Integer getSteps() {
    return steps;
  }

  public void setSteps(Integer steps) {
    this.steps = steps;
  }

  public Integer getCalories() {
    return calories;
  }

  public void setCalories(Integer calories) {
    this.calories = calories;
  }

  public List<Object> getTrackPoints() {
    return trackPoints;
  }

  public void setTrackPoints(List<Object> trackPoints) {
    this.trackPoints = trackPoints;
  }
}