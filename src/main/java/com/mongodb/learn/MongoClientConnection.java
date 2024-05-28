package com.mongodb.learn;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
/**
 * MongoClientConnection
 */
public class MongoClientConnection {
    private static MongoClient mongoClient;

    public static synchronized MongoClient getMongoClient() {
        if (mongoClient == null) {
            ConnectionString connectionString = new ConnectionString(
                    "mongodb+srv://test:jTiILJbHoPe7IG1u@cluster0.tymw48q.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");

        // permite controlar o comportamento da instancia client MongoDB
        // depende de instanciar uma ConnectionString
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build()).build();
            mongoClient = MongoClients.create(settings);

        }
        return mongoClient;
    }
}