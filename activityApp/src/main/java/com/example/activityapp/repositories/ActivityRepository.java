package com.example.activityapp.repositories;

import com.example.activityapp.models.UserActivity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ActivityRepository extends MongoRepository<UserActivity, String> {

  UserActivity findByDate(String date);
  @Query("{'date' : { $gte: ?0, $lt: ?1 } }")
  List<UserActivity> findByDateBetween(String from, String to);
  void deleteByDate(String date);

}
