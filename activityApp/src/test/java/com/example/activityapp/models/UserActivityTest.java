package com.example.activityapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserActivityTest {
    @Id
    private String id;
    private String date;
    private List<ActivitySummary> summary;
    private List<ActivitySegment> segments;
    private Integer caloriesIdle;
    private String lastUpdate;

    private UserActivity testUserActivity;
    private ActivitySummary activitySummary_1;
    private ActivitySummary activitySummary_2;
    private ActivitySummary activitySummary_3;
    private ActivitySegment ActivitySegment_1;
    private ActivitySegment ActivitySegment_2;
    private ActivitySegment ActivitySegment_3;


    @BeforeEach
    void setUp() {
        id = "8291699";
        date = "20130212";

        activitySummary_1 = new ActivitySummary("Running","Cardio",30.0,5.0,5000,200);
        activitySummary_2 = new ActivitySummary("walking","walking",30.0,2.5,2500,100);
        activitySummary_3 = new ActivitySummary("transport","transport",30.0,10.0,0,0);
        summary = Arrays.asList(activitySummary_1,activitySummary_2,activitySummary_3);


        Location location = new Location(47.67645,122.32305);
        List<String> foursquareCategoryIds = Arrays.asList("4bf58dd8d48988d125941735", "4bf58dd8d48988d16d941735", "4f4534884b9074f6e4fb0174");
        Place place = new Place(6552482,"Home","home",location,"4a41c62ff964a52095a51fe3",foursquareCategoryIds);
        List<Object> trackPoints = Arrays.asList(new Object(), new Object(), new Object(),new Object(), new Object());
        Activity activity_1 = new Activity("Running","Cardio",false,"20130211T092952-0800","20130211T133458-0800",30.0,5.0,5000,200,trackPoints);
        Activity activity_2 = new Activity("Walking","Walking",true,"20130211T092952-0800","20130211T133458-0800",40.0,10.0,10000,400,trackPoints);
        Activity activity_3 = new Activity("Transport","Transport",true,"20130211T092952-0800","20130211T133458-0800",40.0,10.0,10000,400,trackPoints);
        List<Activity>activities = Arrays.asList(activity_1,activity_2, activity_3);
        String lastUpdate_1 = "20140317T163450Z";
        ActivitySegment_1 = new ActivitySegment("place","2022-02-21T14:00:00Z","2022-02-21T14:30:00Z",place,activities,lastUpdate_1);
        ActivitySegment_2 = new ActivitySegment("walking","2022-02-21T14:00:00Z","2022-02-21T14:30:00Z",place,activities,lastUpdate_1);
        ActivitySegment_3 = new ActivitySegment("transport","2022-02-21T14:00:00Z","2022-02-21T14:30:00Z",place,activities,lastUpdate_1);

        segments = Arrays.asList(ActivitySegment_1,ActivitySegment_2,ActivitySegment_3);
        caloriesIdle = 1439;
        lastUpdate = "20140801T025223Z";
        testUserActivity = new UserActivity(date,summary,segments,caloriesIdle,lastUpdate);

    }

    @Test
    void getCaloriesIdle() {
        assertEquals(caloriesIdle,testUserActivity.getCaloriesIdle());
    }

    @Test
    void setCaloriesIdle() {
        testUserActivity.setCaloriesIdle(5);
        assertEquals(5,testUserActivity.getCaloriesIdle());

    }

    @Test
    void getLastUpdate() {
        assertEquals(lastUpdate,testUserActivity.getLastUpdate());

    }

    @Test
    void setLastUpdate() {
        testUserActivity.setLastUpdate("test");
        assertEquals("test",testUserActivity.getLastUpdate());
    }

    @Test
    void getDate() {
        assertEquals(date,testUserActivity.getDate());


    }

    @Test
    void setDate() {
        testUserActivity.setDate("test");
        assertEquals("test",testUserActivity.getDate());
    }

    @Test
    void getSummary() {
        assertEquals(summary,testUserActivity.getSummary());
    }

    @Test
    void setSummary() {
        List<ActivitySummary> testsummary = Arrays.asList(activitySummary_1,activitySummary_2);
        testUserActivity.setSummary(testsummary);
        assertEquals(testsummary,testUserActivity.getSummary());

    }

    @Test
    void getSegments() {
        assertEquals(segments,testUserActivity.getSegments());

    }

    @Test
    void setSegments() {
        List<ActivitySegment> testsegments = Arrays.asList(ActivitySegment_1,ActivitySegment_2,ActivitySegment_3);
        testUserActivity.setSegments(testsegments);
        assertEquals(testsegments,testUserActivity.getSegments());
    }

    @Test
    void testToString() {
    }
}