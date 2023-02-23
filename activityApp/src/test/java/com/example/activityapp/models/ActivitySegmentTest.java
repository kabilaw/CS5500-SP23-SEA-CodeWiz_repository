package com.example.activityapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivitySegmentTest {
    private String type;
    private String startTime;
    private String endTime;
    private Place place;
    private List<Activity> activities;
    private String lastUpdate;
    private Location location;
    private List<String> foursquareCategoryIds;
    private ActivitySegment testActivitySegment;


    @BeforeEach
    void setUp() {
        type = "place";
        startTime = "2022-02-21T14:00:00Z";
        endTime = "2022-02-21T14:30:00Z";
        location = new Location(47.67645,122.32305);
        foursquareCategoryIds = Arrays.asList("4bf58dd8d48988d125941735", "4bf58dd8d48988d16d941735", "4f4534884b9074f6e4fb0174");

        place = new Place(6552482,"Home","home",location,"4a41c62ff964a52095a51fe3",foursquareCategoryIds);

        List<Object> trackPoints = Arrays.asList(new Object(), new Object(), new Object(),new Object(), new Object());

        Activity activity_1 = new Activity("Running","Cardio",false,"20130211T092952-0800","20130211T133458-0800",30.0,5.0,5000,200,trackPoints);
        Activity activity_2 = new Activity("Walking","Walking",true,"20130211T092952-0800","20130211T133458-0800",40.0,10.0,10000,400,trackPoints);
        Activity activity_3 = new Activity("Transport","Transport",true,"20130211T092952-0800","20130211T133458-0800",40.0,10.0,10000,400,trackPoints);

        activities = Arrays.asList(activity_1,activity_2, activity_3);
        lastUpdate = "20140317T163450Z";

        testActivitySegment = new ActivitySegment(type,startTime,endTime,place,activities,lastUpdate);

    }

    @Test
    void getType() {
        assertEquals(type,testActivitySegment.getType());
    }

    @Test
    void setType() {
        testActivitySegment.setType("test1");
        assertEquals("test1",testActivitySegment.getType());

    }

    @Test
    void getStartTime() {
        assertEquals(startTime,testActivitySegment.getStartTime());

    }

    @Test
    void setStartTime() {
        testActivitySegment.setStartTime("test2");
        assertEquals("test2",testActivitySegment.getStartTime());


    }

    @Test
    void getEndTime() {
        assertEquals(endTime,testActivitySegment.getEndTime());

    }

    @Test
    void setEndTime() {
        testActivitySegment.setEndTime("test3");
        assertEquals("test3",testActivitySegment.getEndTime());
    }

    @Test
    void getPlace() {
        assertEquals(place,testActivitySegment.getPlace());
    }

    @Test
    void setPlace() {
        Place testplace = new Place(6552482,"test","test",location,"4a41c62ff964a52095a51fe3",foursquareCategoryIds);
        testActivitySegment.setPlace(testplace);
        assertEquals(testplace,testActivitySegment.getPlace());

    }

    @Test
    void getActivities() {
        assertEquals(activities,testActivitySegment.getActivities());
    }

    @Test
    void setActivities() {
        List<Object> trackPoints = Arrays.asList(new Object(), new Object());
        Activity activity_4 = new Activity("Transport","Transport",true,"20130211T092952-0800","20130211T133458-0800",40.0,10.0,10000,400,trackPoints);
        List<Activity> testActivities = Arrays.asList(activity_4);
        testActivitySegment.setActivities(testActivities);
        assertEquals(testActivities,testActivitySegment.getActivities());
    }

    @Test
    void getLastUpdate() {
        assertEquals(lastUpdate,testActivitySegment.getLastUpdate());
    }

    @Test
    void setLastUpdate() {
        testActivitySegment.setLastUpdate("test");
        assertEquals("test",testActivitySegment.getLastUpdate());
    }
}