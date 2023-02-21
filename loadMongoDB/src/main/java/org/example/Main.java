package org.example;

import java.io.*;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import com.mongodb.client.MongoClients;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.mongodb.MongoWriteException;
import org.apache.commons.io.IOUtils;
import org.bson.json.JsonObject;
import javax.naming.StringRefAddr;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Main {
  public static void main(String[] args) throws IOException {

    com.mongodb.client.MongoClient client = MongoClients.create( "mongodb://127.0.0.1/");

    MongoDatabase database = client.getDatabase("personDB2");
    MongoCollection<org.bson.Document> coll = database.getCollection("activityData2");

    try {

      coll.drop();
      // Open the JSON file and parse it
      JSONParser parser = new JSONParser();
      JSONArray data = (JSONArray) parser.parse(new FileReader("input/storyline.json"));
      List<InsertOneModel<Document>> docs = new ArrayList<>();
//       Insert the data into the collection
      for (Object obj : data) {
        Document doc = Document.parse(obj.toString());
//        coll.insertOne(doc);
        docs.add(new InsertOneModel<>(doc));
      }
      coll.bulkWrite(docs, new BulkWriteOptions().ordered(false));

      // Close the MongoDB client
      client.close();

    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }

  }
}