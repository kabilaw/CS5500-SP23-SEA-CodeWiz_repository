package com.example.activityapp.configs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

class MongoConfigTest {
    private MongoProperties mongoProperties;

    private MongoTemplate mongoTemplate;
    private MongoConfig testMongoConfig;


    @Test
    void mongoTemplate() throws Exception {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void testMongoTemplate() {
    }
}