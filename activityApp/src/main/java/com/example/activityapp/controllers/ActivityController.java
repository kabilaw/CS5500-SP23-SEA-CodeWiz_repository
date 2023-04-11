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
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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

//  @GetMapping(value = "/assistant")
//  public String getAssistantMessage(@RequestParam int dailyCalories, @RequestParam String question){
//    if (!ObjectUtils.isEmpty(dailyCalories) && !ObjectUtils.isEmpty(question)) {
//      String gptResponse = gptService.getChatGptResponse(dailyCalories, question);
////      Map<String, Object> response = new HashMap<>();
////      response.put("calories", dailyCalories);
////      response.put("ChatGPTResponse", gptResponse);
//      return gptResponse;
//    }
//    throw new MissingParameterException("Missing required parameters");
//  }


  @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST})
  @GetMapping(value = "/assistant", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter getAssistantMessage(@RequestParam int dailyCalories, @RequestParam String question) {
    if (!ObjectUtils.isEmpty(dailyCalories) && !ObjectUtils.isEmpty(question)) {
      SseEmitter gptResponse = gptService.getChatGptResponse(dailyCalories, question);
      return gptResponse;
    }
    throw new MissingParameterException("Missing required parameters");
  }

  @PostMapping("/insertActivity")
  public void insertActivity(@RequestBody UserActivity payload) {
    if (payload != null) {
      activityService.insertActivity(payload);
      return;
    }
    //throw new ActivityNotFoundException("Not a valid payload.");
  }

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

  @DeleteMapping(value = "/byDate/{date}")
  public void deleteActivityByDate(@PathVariable String date) {
    if (!ObjectUtils.isEmpty(date)) {
      activityService.deleteByDate(date);
      return;
    }
    throw new MissingParameterException("Missing required parameter 'date'");
  }
}
