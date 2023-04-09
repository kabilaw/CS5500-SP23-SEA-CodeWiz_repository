package com.example.activityapp.services;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class GptServiceImpl implements GptService {

  @Autowired
  private Environment env;
  @Override
  public String getChatGptResponse(int calories) {
    String gptAPIkey = env.getProperty("gpt.api.key");
    String question = String.format("what can i do to burn more calories if i had burned %s calories today? provide the answer in 350 or less words also provide the number of calories i burned in your answer", calories);
    OpenAiService service = new OpenAiService(gptAPIkey,
        Duration.ofSeconds(30));
    ChatMessage chatMessage = new ChatMessage("user", question);
    List<ChatMessage> chatMessages = new ArrayList<>();
    chatMessages.add(chatMessage);
    ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
        .messages(chatMessages)
        .model("gpt-3.5-turbo")
        .user("user")
        .logitBias(new HashMap<>())
        .maxTokens(350)
        .n(1)
        .build();
    List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();
    List<String> responses = new ArrayList<>();
    for (ChatCompletionChoice choice: choices) {
      responses.add(choice.getMessage().getContent());
    }
    return responses.get(0);
  }


  HashMap<String, String> questionList = new HashMap<>() {{
    put("1", "i burned %s calories today, is it a good amount generally speaking? provide your answer in 350 words or less and include the number of calories i burned in your answer");
    put("2", "i burned %s calories today, how can i burn more calories? provide your answer in 350 words or less and include the number of calories i burned in your answer");
    put("3", "i burned %s calories today, do you recommend a certain diet? provide your answer in 350 words or less and include the number of calories i burned in your answer");
    put("4", "i burned %s calories today, what kind of sports burns more calories? provide your answer in 350 words or less and include the number of calories i burned in your answer");
  }};

  @Override
  public String getChatGptResponse(int calories, String question) {
    String gptAPIkey = env.getProperty("gpt.api.key");
    String currentQuestion = questionList.get(question);
    String q = String.format(currentQuestion, calories);
    OpenAiService service = new OpenAiService(gptAPIkey,
        Duration.ofSeconds(30));
    ChatMessage chatMessage = new ChatMessage("user", q);
    List<ChatMessage> chatMessages = new ArrayList<>();
    chatMessages.add(chatMessage);
    ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
        .messages(chatMessages)
        .model("gpt-3.5-turbo")
        .user("user")
        .logitBias(new HashMap<>())
        .maxTokens(350)
        .n(1)
        .build();
    List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();
    List<String> responses = new ArrayList<>();
    for (ChatCompletionChoice choice: choices) {
      responses.add(choice.getMessage().getContent());
    }
    return responses.get(0);
  }

}
