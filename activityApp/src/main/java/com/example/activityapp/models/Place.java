package com.example.activityapp.models;

import java.util.List;

public class Place {
  private int id;
  private String name;
  private String type;
  private Location location;
  private String foursquareId;
  private List<String> foursquareCategoryIds;

  public Place() {
  }

  public Place(int id, String name, String type, Location location, String foursquareId,
      List<String> foursquareCategoryIds) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.location = location;
    this.foursquareId = foursquareId;
    this.foursquareCategoryIds = foursquareCategoryIds;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getFoursquareId() {
    return foursquareId;
  }

  public void setFoursquareId(String foursquareId) {
    this.foursquareId = foursquareId;
  }

  public List<String> getFoursquareCategoryIds() {
    return foursquareCategoryIds;
  }

  public void setFoursquareCategoryIds(List<String> foursquareCategoryIds) {
    this.foursquareCategoryIds = foursquareCategoryIds;
  }
}
