package com.example.activityapp.configs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

  @Autowired
  private MongoProperties mongoProperties;

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    MongoClient mongoClient = MongoClients.create(mongoProperties.getUri());
    MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoProperties.getDatabase());
    return mongoTemplate;
  }

}