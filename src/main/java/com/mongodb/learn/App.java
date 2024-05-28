package com.mongodb.learn;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try (MongoClient client = MongoClientConnection.getMongoClient()) {
            Document post = new Document("_id", new ObjectId()).append("name", "John Doe").append("age", 31);

            //CRUD
            Crud crud = new Crud(MongoClientConnection.getMongoClient());
            //INSERT MANY
            crud.insertOneDocument(post);
        }
    }
}
