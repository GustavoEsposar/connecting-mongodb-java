package com.mongodb.learn;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.BsonValue;


public class Crud {
    private final MongoCollection<Document> collection;

    public Crud(MongoClient client) {
        this.collection = client.getDatabase("blog").getCollection("posts");
    }

    public void insertOneDocument(Document doc) {
        System.out.println("Inserting one post document");
        InsertOneResult result = collection.insertOne(doc);
        BsonValue id = result.getInsertedId();
        System.out.println("Inserted document Id: " + id);
      }
}
