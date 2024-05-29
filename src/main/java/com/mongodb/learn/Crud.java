package com.mongodb.learn;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;
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

    public void showPosts() {
        MongoCursor<Document> cursor = this.collection.find().iterator();

        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }
    }

    public void findDocuments(Bson query) {
        try(MongoCursor<Document> cursor = collection.find(query).iterator()) {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }

    public void updateOnePostContent(String name, String content) {
        Bson query = Filters.eq("name", name);
        Bson updates = Updates.combine(Updates.set("conetn", content));
        UpdateResult res = collection.updateOne(query, updates);
    }
}
