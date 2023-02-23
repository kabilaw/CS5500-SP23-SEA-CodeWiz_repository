package com.example.activityapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private Double lat;
    private Double lon;
    private Location testLocation;

    @BeforeEach
    void setUp() {
        lat = 47.59786388589972;
        lon = -122.3280325497587;
        testLocation = new Location(lat,lon);

    }

    @Test
    void getLat() {
        assertEquals(lat,testLocation.getLat());
    }

    @Test
    void setLat() {
        testLocation.setLat(10.01);
        assertEquals(10.01,testLocation.getLat());


    }

    @Test
    void getLon() {
        assertEquals(lon,testLocation.getLon());

    }

    @Test
    void setLon() {
        testLocation.setLon(-10.01);
        assertEquals(-10.01,testLocation.getLon());

    }
}