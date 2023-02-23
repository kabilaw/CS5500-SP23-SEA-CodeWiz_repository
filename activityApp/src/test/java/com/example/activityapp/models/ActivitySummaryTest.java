package com.example.activityapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivitySummaryTest {
    private String activity;
    private String group;
    private double duration;
    private double distance;
    private int steps;
    private int calories;
    private ActivitySummary testActivitySummary;


    @BeforeEach
    void setUp() {
        activity = "Running";
        group = "Cardio";
        duration = 30.0;
        distance = 5.0;
        steps = 5000;
        calories = 200;

        testActivitySummary = new ActivitySummary(activity,group,duration,distance,steps,calories);

    }

    @Test
    void getActivity() {
        assertEquals(activity,testActivitySummary.getActivity());
    }

    @Test
    void setActivity() {
        testActivitySummary.setActivity("test");
        assertEquals("test",testActivitySummary.getActivity());
    }

    @Test
    void getGroup() {
        assertEquals(group,testActivitySummary.getGroup());

    }

    @Test
    void setGroup() {
        testActivitySummary.setGroup("test");
        assertEquals("test",testActivitySummary.getGroup());
    }

    @Test
    void getDuration() {
        assertEquals(duration,testActivitySummary.getDuration());
    }

    @Test
    void setDuration() {
        assertEquals(duration,testActivitySummary.getDuration());
    }

    @Test
    void getDistance() {
        assertEquals(distance,testActivitySummary.getDistance());
    }

    @Test
    void setDistance() {
        testActivitySummary.setDistance(1.0);
        assertEquals(1.0,testActivitySummary.getDistance());
    }

    @Test
    void getSteps() {
        assertEquals(steps,testActivitySummary.getSteps());

    }

    @Test
    void setSteps() {
        testActivitySummary.setSteps(10);
        assertEquals(10,testActivitySummary.getSteps());
    }

    @Test
    void getCalories() {
        assertEquals(calories,testActivitySummary.getCalories());
    }

    @Test
    void setCalories() {
        testActivitySummary.setCalories(40);
        assertEquals(40,testActivitySummary.getCalories());
    }
}