package com.example.activityapp.models;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "activityData")
public class UserActivity {

  @Id
  private String id;
  private String date;
  private List<ActivitySummary> summary;
  private List<ActivitySegment> segments;
  private Integer caloriesIdle;
  private String lastUpdate;

  public UserActivity() {
  }

  public UserActivity(String date, List<ActivitySummary> summary, List<ActivitySegment> segments, Integer caloriesIdle, String lastUpdate) {
    this.date = date;
    this.summary = summary;
    this.segments = segments;
    this.caloriesIdle = caloriesIdle;
    this.lastUpdate = lastUpdate;
  }

  public Integer getCaloriesIdle() {
    return caloriesIdle;
  }

  public void setCaloriesIdle(Integer caloriesIdle) {
    this.caloriesIdle = caloriesIdle;
  }

  public String getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(String lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<ActivitySummary> getSummary() {
    return summary;
  }

  public void setSummary(List<ActivitySummary> summary) {
    this.summary = summary;
  }

  public List<ActivitySegment> getSegments() {
    return segments;
  }

  public void setSegments(List<ActivitySegment> segments) {
    this.segments = segments;
  }

  @Override
  public String toString() {
    return "UserActivity{" +
        "id='" + id + '\'' +
        ", date='" + date + '\'' +
        ", summary=" + summary +
        ", segments=" + segments +
        '}';
  }
}