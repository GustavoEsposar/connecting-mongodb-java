package com.mongodb.learn;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.TransactionBody;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import org.bson.BsonValue;

public class Crud {
    private final MongoCollection<Document> collection;
    private final ClientSession clientSession;

    public Crud(MongoClient client) {
        this.collection = client.getDatabase("blog").getCollection("posts");
        this.clientSession = client.startSession();
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
        try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }

    public void updateOnePostContent(String name, String content) {
        Bson query = Filters.eq("name", name);
        Bson updates = Updates.combine(Updates.set("conetn", content));
        UpdateResult res = collection.updateOne(query, updates);
    }

    public void removeOnePost(Bson query) {
        DeleteResult res = collection.deleteOne(query);
        System.out.println("Deleted a document:");
        System.out.println("\t" + res.getDeletedCount());
    }

    public void matchStage(Bson filter) {
        Bson matchStage = Aggregates.match(filter);
        System.out.println("Display aggregation results");
        collection.aggregate(Arrays.asList(matchStage)).forEach(document->System.out.print(document.toJson()));
    }
}
