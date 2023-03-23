package com.example.activityapp.services;

import com.example.activityapp.models.UserActivity;
import java.util.List;

public interface ActivityService {

  List<UserActivity> findAll();
  UserActivity findByDate(String date);
  List<UserActivity> findByDateBetween(String from, String to);
  List<UserActivity> getActivitiesBetweenDates(String startDateString, String endDateString);
  UserActivity getActivityByDateAndType(String date, String type);
  int getActivityCaloriesByDate(String date);
  void deleteByDate(String date);
  void insertActivity(UserActivity payload);
}
