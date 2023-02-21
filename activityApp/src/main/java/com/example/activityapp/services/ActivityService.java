package com.example.activityapp.services;

import com.example.activityapp.models.UserActivity;
import java.util.List;

public interface ActivityService {

  public List<UserActivity> findAll();
  public UserActivity findByDate(String date);
  public List<UserActivity> findByDateBetween(String from, String to);
  public List<UserActivity> getActivitiesBetweenDates(String startDateString, String endDateString);
  public UserActivity getActivityByDateAndType(String date, String type);
public UserActivity getActivityByTypeAndGroup(String date, String type, String group);


}
