package com.example.activityapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {
    private int id;
    private String name;
    private String type;
    private Location location;
    private String foursquareId;
    private List<String> foursquareCategoryIds;
    private Place testPlace;

    @BeforeEach
    void setUp() {
        id = 8291699;
        name = "A.R.O.";
        type = "foursquare";
        location = new Location(47.59786388589972, -122.3280325497587);
        foursquareId = "4e32f5c3fa76f00388bbbf06";
        foursquareCategoryIds = Arrays.asList("4bf58dd8d48988d125941735", "4bf58dd8d48988d16d941735", "4f4534884b9074f6e4fb0174");
        testPlace = new Place(id,name,type,location,foursquareId,foursquareCategoryIds);


    }

    @Test
    void getId() {
        assertEquals(id,testPlace.getId());
    }

    @Test
    void setId() {
        testPlace.setId(1010);
        assertEquals(1010,testPlace.getId());
    }

    @Test
    void getName() {
        assertEquals(name,testPlace.getName());
    }

    @Test
    void setName() {
        testPlace.setName("test");
        assertEquals("test",testPlace.getName());
    }

    @Test
    void getType() {
        assertEquals(type,testPlace.getType());
    }

    @Test
    void setType() {
        testPlace.setType("test");
        assertEquals("test",testPlace.getType());
    }

    @Test
    void getLocation() {
        assertEquals(location,testPlace.getLocation());
    }

    @Test
    void setLocation() {
        Location testLocation = new Location(10.0,-10.0);
        testPlace.setLocation(testLocation);
        assertEquals(testLocation,testPlace.getLocation());

    }

    @Test
    void getFoursquareId() {
        assertEquals(foursquareId,testPlace.getFoursquareId());
    }

    @Test
    void setFoursquareId() {
        testPlace.setFoursquareId("test");
        assertEquals("test",testPlace.getFoursquareId());
    }

    @Test
    void getFoursquareCategoryIds() {
        assertEquals(foursquareCategoryIds,testPlace.getFoursquareCategoryIds());

    }

    @Test
    void setFoursquareCategoryIds() {
        List<String> testfoursquareCategoryIds = Arrays.asList("4bf58dd8d48988d125941735", "4bf58dd8d48988d16d941735");
        testPlace.setFoursquareCategoryIds(testfoursquareCategoryIds);
        assertEquals(testfoursquareCategoryIds,testPlace.getFoursquareCategoryIds());
    }
}