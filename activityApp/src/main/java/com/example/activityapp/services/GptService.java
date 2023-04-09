package com.example.activityapp.services;

public interface GptService {
  String getChatGptResponse(int calories);

  String getChatGptResponse(int calories, String question);

}
