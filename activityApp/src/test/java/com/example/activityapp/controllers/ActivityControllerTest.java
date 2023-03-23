package com.example.activityapp.controllers;

import com.example.activityapp.models.UserActivity;
import com.example.activityapp.services.ActivityService;
import com.example.activityapp.services.GptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ActivityControllerTest {

    private ActivityController activityController;
    private ActivityService activityService;

    private GptService gptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        activityController = new ActivityController(activityService, gptService);

    }

    @Test
    void getActivityByDate() {

    }

    @Test
    void getActivityByDateBetweenT() {
    }

    @Test
    void getActivityByType() {
    }

    @Test
    void getActivityByGroup() {
    }

    @Test
    void getActivities() {
    }

    @Test
    void testGetActivityByDate() {
    }

    @Test
    void testGetActivityByDateBetweenT() {
    }

    @Test
    void testGetActivityByType() {
    }

    @Test
    void testGetActivityByGroup() {
    }

    @Test
    void testGetActivities() {
    }
}