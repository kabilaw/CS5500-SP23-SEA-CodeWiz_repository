package com.example.activityapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {
    private String activity;
    private String group;
    private Boolean manual;
    private String startTime;
    private String endTime;
    private Double duration;
    private Double distance;
    private Integer steps;
    private Integer calories;
    private List<Object> trackPoints;
    private Activity testActivity;






    @BeforeEach
    void setUp() {
        activity = "Running";
        group = "Cardio";
        manual = false;
        startTime = "20130211T092952-0800";
        endTime = "20130211T133458-0800";
        duration = 30.0;
        distance = 5.0;
        steps = 5000;
        calories = 200;
        trackPoints = Arrays.asList(new Object(), new Object(), new Object(),new Object(), new Object());

        testActivity = new Activity(activity, group, manual, startTime, endTime, duration, distance, steps, calories, trackPoints);


    }

    @Test
    void getActivity() {
        assertEquals(activity, testActivity.getActivity());
    }

    @Test
    void setActivity() {
        testActivity.setActivity("test");
        assertEquals("test", testActivity.getActivity());


    }

    @Test
    void getGroup() {
        assertEquals(group, testActivity.getGroup());
    }

    @Test
    void setGroup() {
        testActivity.setGroup("test");
        assertEquals("test", testActivity.getGroup());
    }

    @Test
    void getManual() {
        assertEquals(manual, testActivity.getManual());
    }

    @Test
    void setManual() {
        testActivity.setManual(true);
        assertEquals(true, testActivity.getManual());
    }

    @Test
    void getStartTime() {
        assertEquals(startTime, testActivity.getStartTime());
    }

    @Test
    void setStartTime() {
        testActivity.setStartTime("test");
        assertEquals("test", testActivity.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(endTime, testActivity.getEndTime());
    }

    @Test
    void setEndTime() {
        testActivity.setEndTime("test");
        assertEquals("test", testActivity.getEndTime());
    }

    @Test
    void getDuration() {
        assertEquals(duration, testActivity.getDuration());
    }

    @Test
    void setDuration() {
        testActivity.setDuration(100.0);
        assertEquals(100.0,testActivity.getDuration());

    }

    @Test
    void getDistance() {
        assertEquals(distance, testActivity.getDistance());
    }

    @Test
    void setDistance() {
        testActivity.setDistance(1.0);
        assertEquals(1.0, testActivity.getDistance());
    }

    @Test
    void getSteps() {
        assertEquals(steps, testActivity.getSteps());
    }

    @Test
    void setSteps() {
        testActivity.setSteps(100);
        assertEquals(100, testActivity.getSteps());
    }

    @Test
    void getCalories() {
        assertEquals(calories, testActivity.getCalories());
    }

    @Test
    void setCalories() {
        testActivity.setCalories(100);
        assertEquals(100, testActivity.getCalories());
    }

    @Test
    void getTrackPoints() {
        assertEquals(trackPoints, testActivity.getTrackPoints());
    }

    @Test
    void setTrackPoints() {
        List<Object> test = Arrays.asList(new Object(), new Object());
        testActivity.setTrackPoints(test);
        assertEquals(test, testActivity.getTrackPoints());
    }
}