package com.example.activityapp.models;

import java.util.List;

public class ActivitySegment {
  private String type;
  private String startTime;
  private String endTime;
  private Place place;
  private List<Activity> activities;
  private String lastUpdate;

  public ActivitySegment() {
  }

  public ActivitySegment(String type, String startTime, String endTime, Place place,
      List<Activity> activities, String lastUpdate) {
    this.type = type;
    this.startTime = startTime;
    this.endTime = endTime;
    this.place = place;
    this.activities = activities;
    this.lastUpdate = lastUpdate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }

  public List<Activity> getActivities() {
    return activities;
  }

  public void setActivities(List<Activity> activities) {
    this.activities = activities;
  }

  public String getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(String lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}