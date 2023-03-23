package com.example.activityapp.services;

import com.example.activityapp.exceptions.ActivityNotFoundException;
import com.example.activityapp.models.ActivitySegment;
import com.example.activityapp.models.ActivitySummary;
import com.example.activityapp.models.UserActivity;
import com.example.activityapp.repositories.ActivityRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ActivityServiceImpl implements ActivityService {
  @Autowired
  private ActivityRepository activityRepository;

//  MongoTemplate Section
  @Autowired
  private MongoTemplate mongoTemplate;

// Use the MongoRepository method instead for this one
  @Override
  public List<UserActivity> getActivitiesBetweenDates(String startDateString, String endDateString) {
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//    LocalDate startDate = LocalDate.parse(startDateString, formatter);
//    LocalDate endDate = LocalDate.parse(endDateString, formatter);
    Criteria criteria = Criteria.where("date").gte(startDateString).lt(endDateString);
    Query query = new Query(criteria);
    return mongoTemplate.find(query, UserActivity.class);
  }

  @Override
  public UserActivity getActivityByDateAndType(String date, String type) {
    Query query = new Query(Criteria.where("date").is(date));
    UserActivity activity = mongoTemplate.findOne(query, UserActivity.class);

    if (activity == null) {
      throw new ActivityNotFoundException(String.format("Activity with date %s not found", date));
    }

    List<ActivitySegment> filteredSegments = activity.getSegments().stream()
        .filter(segment -> segment.getType().equals(type))
        .collect(Collectors.toList());
    activity.setSegments(filteredSegments);

    return activity;
  }
  @Override
  public int getActivityCaloriesByDate(String date) {
    UserActivity activity = findByDate(date);
    if (activity == null) {
      throw new ActivityNotFoundException(String.format("Activity with date %s not found", date));
    }
    List<ActivitySummary> summaries = activity.getSummary();
    int dailyCalories = 0;
    for (ActivitySummary summary: summaries) {
      dailyCalories += summary.getCalories();
    }


    return dailyCalories;
  }
  public void insertActivity(UserActivity payload) {
    //if value exists, then don't add.
    String date = payload.getDate();
    UserActivity userActivity = findByDate(date);
    if (ObjectUtils.isEmpty(userActivity)) {
      activityRepository.save(payload);
      return;
    }
    throw new RuntimeException("This record is not unique.");

  }

//  MongoTemplate section END
//  @Override
//  public void insertRecord(UserActivity payload) {
//    // Insert the document into your collection
//    String date = payload.getDate();
//    UserActivity userActivity = findByDate(date);
//    if (ObjectUtils.isEmpty(userActivity)) {
//      activityRepository.save(payload);
//      return;
//    }
//    throw new RuntimeException("Non Unique record");
//  }

  @Override
  public UserActivity findByDate(String date) {
    UserActivity userActivity = activityRepository.findByDate(date);
    return userActivity;
  }

  @Override
  public List<UserActivity> findByDateBetween(String from, String to) {
    return activityRepository.findByDateBetween(from,to);
  }

  @Override
  public List<UserActivity> findAll() {
    return activityRepository.findAll();
  }
  public void deleteByDate(String date) {
    UserActivity userActivity = findByDate(date);
    if (!ObjectUtils.isEmpty(userActivity)) {
      activityRepository.deleteByDate(date);
      return;
    }
    throw new ActivityNotFoundException(String.format("Activity with date %s not found", date));
  }
}
