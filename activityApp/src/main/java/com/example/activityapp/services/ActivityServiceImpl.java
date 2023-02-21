package com.example.activityapp.services;

import com.example.activityapp.models.ActivitySegment;
import com.example.activityapp.models.UserActivity;
import com.example.activityapp.repositories.ActivityRepository;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
//@EnableMongoRepositories
public class ActivityServiceImpl implements ActivityService {
  @Autowired
  private ActivityRepository activityRepository;
//  constructor not required
//  public ActivityServiceImpl(ActivityRepository activityRepository) {
//    this.activityRepository = activityRepository;
//  }

//  MongoTemplate Section
  @Autowired
  private MongoTemplate mongoTemplate;
//  constructor not required
//  public ActivityServiceImpl(MongoTemplate mongoTemplate) {
//    this.mongoTemplate = mongoTemplate;
//  }
// Use the MongoRepository method instead for this one
  public List<UserActivity> getActivitiesBetweenDates(String startDateString, String endDateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//    LocalDate startDate = LocalDate.parse(startDateString, formatter);
//    LocalDate endDate = LocalDate.parse(endDateString, formatter);
    Criteria criteria = Criteria.where("date").gte(startDateString).lt(endDateString);
    Query query = new Query(criteria);
    return mongoTemplate.find(query, UserActivity.class);
  }

//  public UserActivity findActivityByDateAndType(String date, String type) {
//    UserActivity activity = activityRepository.findByDate(date);
//    if (activity == null) {
//      return null;
//    }
//    List<ActivitySegment> segments = activityRepository.findSegmentsByType(type);
//    activity.setSegments(segments);
//    return activity;
//  }

  public UserActivity getActivityByDateAndType(String date, String type) {
    Query query = new Query(Criteria.where("date").is(date));
    UserActivity activity = mongoTemplate.findOne(query, UserActivity.class);

    if (activity == null) {
      throw new RuntimeException("Activity not found for date: " + date);
    }

    List<ActivitySegment> filteredSegments = activity.getSegments().stream()
        .filter(segment -> segment.getType().equals(type))
        .collect(Collectors.toList());
    activity.setSegments(filteredSegments);

    return activity;
  }

  public UserActivity getActivityByTypeAndGroup(String date, String type, String group) {
    Query query = new Query(Criteria.where("date").is(date));
    UserActivity activity = mongoTemplate.findOne(query, UserActivity.class);

    if (activity == null) {
      throw new RuntimeException("Activity not found for date: " + date);
    }

    List<ActivitySegment> filteredSegments = activity.getSegments().stream()
        .filter(segment -> segment.getType().equals(type))
        .collect(Collectors.toList());
    activity.setSegments(filteredSegments);

//    List<Activity> filteredActivities = filteredSegments.stream().filter(.)
    return activity;
  }

//  MongoTemplate section END

  @Override
  public UserActivity findByDate(String date) {
    return activityRepository.findByDate(date);
  }

  @Override
  public List<UserActivity> findByDateBetween(String from, String to) {
    return activityRepository.findByDateBetween(from,to);
  }

  @Override
  public List<UserActivity> findAll() {
    return activityRepository.findAll();
  }
}
