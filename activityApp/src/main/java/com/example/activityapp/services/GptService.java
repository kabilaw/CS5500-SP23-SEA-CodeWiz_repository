package com.example.activityapp.services;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface GptService {
  String getChatGptResponse(int calories);

//  String getChatGptResponse(int calories, String question);
  SseEmitter getChatGptResponse(int calories, String question);

}
