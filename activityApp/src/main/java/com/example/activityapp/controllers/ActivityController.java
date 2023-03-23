package com.example.activityapp.controllers;

import com.example.activityapp.exceptions.ActivityNotFoundException;
import com.example.activityapp.exceptions.MissingParameterException;
import com.example.activityapp.models.UserActivity;
import com.example.activityapp.services.ActivityService;
import com.example.activityapp.services.GptService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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

  @Autowired
  private GptService gptService;

  public ActivityController(ActivityService activityService, GptService gptService) {
    this.activityService = activityService;
    this.gptService = gptService;
  }

//  the below method fetches a record by date with the option to filter segments by type
  @GetMapping(value = "/byDate/{date}")
  public UserActivity getActivityByDate(@PathVariable String date, @RequestParam(value = "type", required = false) String type){
    if (!ObjectUtils.isEmpty(type)){
      return activityService.getActivityByDateAndType(date, type);
    }
    if (!ObjectUtils.isEmpty(date)) {
      return activityService.findByDate(date);
    }
    throw new ActivityNotFoundException(String.format("Activity with date %s and type %s not found", date, type));
  }

  @GetMapping(value = "/byDate/{date}/calories")
  public Map<String, Object> getActivityByDate(@PathVariable String date){
    if (!ObjectUtils.isEmpty(date)) {
      int dailyCalories = activityService.getActivityCaloriesByDate(date);
      String gptResponse = gptService.getChatGptResponse(dailyCalories);
      Map<String, Object> response = new HashMap<>();
      response.put("calories", dailyCalories);
      response.put("ChatGPTResponse", gptResponse);
      return response;
    }
    throw new MissingParameterException("Missing required parameter 'date'");
  }


//  @PostMapping("/insertRecord")
//  public void insertRecord(@RequestBody UserActivity payload) {
//    activityService.insertRecord(payload);
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

  @GetMapping(value = "/allActivities")
  public List<UserActivity> getActivities() {
    return activityService.findAll();
  }

}
