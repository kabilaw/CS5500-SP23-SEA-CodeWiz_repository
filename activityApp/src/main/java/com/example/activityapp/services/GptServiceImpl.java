package com.example.activityapp.services;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.service.SSE;
import io.reactivex.Flowable;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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

//  a method to get GPT response in one response instead of chunks
//  @Override
//  public String getChatGptResponse(int calories, String question) {
//    String gptAPIkey = env.getProperty("gpt.api.key");
//    String currentQuestion = questionList.get(question);
//    String q = String.format(currentQuestion, calories);
//    OpenAiService service = new OpenAiService(gptAPIkey,
//        Duration.ofSeconds(30));
//    ChatMessage chatMessage = new ChatMessage("user", q);
//    List<ChatMessage> chatMessages = new ArrayList<>();
//    chatMessages.add(chatMessage);
//    ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
//        .messages(chatMessages)
//        .model("gpt-3.5-turbo")
//        .user("user")
//        .logitBias(new HashMap<>())
//        .maxTokens(350)
//        .n(1)
//        .stream(true)
//        .build();
//
//
////    List<ChatCompletionChoice> choices = service.createChatCompletion(chatCompletionRequest).getChoices();
////    List<String> responses = new ArrayList<>();
////    for (ChatCompletionChoice choice: choices) {
////      responses.add(choice.getMessage().getContent());
////    }
////    return responses.get(0);
//  }

  @Override
  public SseEmitter getChatGptResponse(int calories, String question) {
//    ResponseBodyEmitter emitter = new ResponseBodyEmitter();
    String gptAPIkey = env.getProperty("gpt.api.key");
    String currentQuestion = questionList.get(question);
    String q = String.format(currentQuestion, calories);
    OpenAiService service = new OpenAiService(gptAPIkey,
        Duration.ofSeconds(30));
    ChatMessage chatMessage = new ChatMessage("user", q);
    List<ChatMessage> chatMessages = new ArrayList<>();
    chatMessages.add(chatMessage);
    ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
        .messages(chatMessages)
        .model("gpt-3.5-turbo")
        .user("user")
        .logitBias(new HashMap<>())
        .n(1)
        .stream(true)
        .build();

    SseEmitter sseEmitter = new SseEmitter();
    service.streamChatCompletion(chatCompletionRequest)
        .subscribe(chatCompletionChunk -> {
          List<ChatCompletionChoice> choices = chatCompletionChunk.getChoices();
          if (chatCompletionChunk.getChoices().get(0).getFinishReason() == "stop") sseEmitter.complete();
          for (ChatCompletionChoice choice : choices) {
            try {
              sseEmitter.send(choice.getMessage());
            } catch (IOException e) {
              sseEmitter.completeWithError(e);
            }
          }
        }, sseEmitter::completeWithError, sseEmitter::complete);

    sseEmitter.onCompletion(sseEmitter::complete);
    sseEmitter.onError(sseEmitter::completeWithError);
    return sseEmitter;

//    List<ChatCompletionChunk> chunks = new ArrayList<>();
//    service.streamChatCompletion(chatCompletionRequest).blockingForEach((k) -> {
//      chunks.add(k);
//      try {
//        System.out.println(k.getChoices().get(0).getMessage());
////        if(k.getChoices().get(0).getMessage().getContent() == null) sseEmitter.complete();
//        sseEmitter.send(k.getChoices().get(0).getMessage());
//      } catch (IOException e) {
//        sseEmitter.completeWithError(e);
//      }
//
//    });
//    sseEmitter.onCompletion(sseEmitter::complete);
//    sseEmitter.onError(sseEmitter::completeWithError);
//    return sseEmitter;
  }

}
