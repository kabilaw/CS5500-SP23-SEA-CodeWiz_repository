package com.example.activityapp.services;

import com.example.activityapp.repositories.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

class ActivityServiceImplTest {
    private ActivityRepository activityRepository;
    private MongoTemplate mongoTemplate;
    @BeforeEach
    void setUp() {

    }

    @Test
    void getActivitiesBetweenDates() {
    }

    @Test
    void getActivityByDateAndType() {
    }

    @Test
    void getActivityByTypeAndGroup() {
    }

    @Test
    void findByDate() {
    }

    @Test
    void findByDateBetween() {
    }

    @Test
    void findAll() {
    }
}