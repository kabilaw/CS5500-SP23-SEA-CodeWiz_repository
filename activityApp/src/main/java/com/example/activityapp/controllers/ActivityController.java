package com.example.activityapp.controllers;

import com.example.activityapp.models.UserActivity;
import com.example.activityapp.services.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class ActivityController {

  @Autowired
  private ActivityService activityService;

  public ActivityController(ActivityService activityService) {
    this.activityService = activityService;
  }

//  @GetMapping(value = "/byDate/{date}")
//  public UserActivity getActivityByDate(@PathVariable String date){
//    UserActivity userActivity = activityService.findByDate(date);
////    if (userActivity == null) {
////      return null;
////    }
//    return userActivity;
//  }

//  the below method fetches a record by date with the option to filter segments by type
  @GetMapping(value = "/byDate/{date}")
  public UserActivity getActivityByDate(@PathVariable String date, @RequestParam(value = "type", required = false) String type){
    if (type != null){
      UserActivity userActivity = activityService.getActivityByDateAndType(date, type);
      return userActivity;
    }
    if (date != null) {
      UserActivity userActivity = activityService.findByDate(date);
      return userActivity;
    }
    return null;
  }

//  the below method uses mongoRepository to fetch documents from Database
//  @GetMapping(value = "/byDate/{start}/{end}")
//  public List<UserActivity> getActivityByDateBetween(@PathVariable("start") String startDateString,
//      @PathVariable("end") String endDateString){
//    List<UserActivity> userActivities = activityService.findByDateBetween(startDateString, endDateString);
//    return userActivities;
//  }

//  the below method uses mongoTemplate approach to fetch documents from Database
  @GetMapping(value = "/byDate/{start}/{end}")
  public List<UserActivity> getActivityByDateBetweenT(@PathVariable("start") String startDateString,
      @PathVariable("end") String endDateString){
    List<UserActivity> userActivities = activityService.getActivitiesBetweenDates(startDateString, endDateString);
    return userActivities;
  }

  @GetMapping(value = "/byType/{date}/{type}")
  public UserActivity getActivityByType(@PathVariable("date") String date, @PathVariable("type") String type){
    UserActivity userActivity = activityService.getActivityByDateAndType(date, type);
    return userActivity;
  }
  @GetMapping(value = "/byGroup/{date}/{type}/{group}")
  public UserActivity getActivityByGroup(@PathVariable("date") String date, @PathVariable("type") String type, @PathVariable("group") String group){
    UserActivity userActivity = activityService.getActivityByTypeAndGroup(date, type, group);
    return userActivity;
  }

// the below method uses RequestParam annotation instead of PathVariable
//  @GetMapping(value = "/byDate")
//  public List<UserActivity> getActivityByDateBetween(@RequestParam("start") String startDateString,
//      @RequestParam("end") String endDateString){
//    List<UserActivity> userActivities = activityService.findByDateBetween(startDateString, endDateString);
//    if (userActivities == null) {
//      return null;
//    }
//    return userActivities;
//  }

  @GetMapping(value = "/allActivities")
  public List<UserActivity> getActivities() {
    return activityService.findAll();
  }

}
