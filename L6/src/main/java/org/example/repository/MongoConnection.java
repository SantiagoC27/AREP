package org.example.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.models.Message;

import com.mongodb.MongoClientURI;

import java.util.Date;

public class MongoConnection {

    private MongoClientURI uri;
    private MongoClient client;
    private MongoDatabase database;

    public MongoConnection(){
        String host = "cluster0.boebnnz.mongodb.net";
        int port = 27017; // El puerto por defecto de MongoDB
        String username = "cardenassantiago27";
        String password = "1Sv2TEQkaaznWqAQ";
        String databaseName = "db";

        uri = new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/?retryWrites=true&w=majority&appName=AtlasApp");
        client = new MongoClient(uri);
        database = client.getDatabase(databaseName);
    }
    public void insertMessage(Message message) {
        MongoCollection<Document> collection = database.getCollection("Message");
        Document document = new Document()
                .append("mensaje", message.getMessage())
                .append("usuario", message.getUser())
                .append("fecha", new Date().toString());
        collection.insertOne(document);
    }

    public String getData() {
        MongoCollection<Document> collection = database.getCollection("Message");
        StringBuilder messageBuilder = new StringBuilder();
        FindIterable<Document> iterable = collection.find();

        for (Document document : iterable){
            messageBuilder.append("<tr><td>")
                    .append(document.get("mensaje"))
                    .append("<tr><td>")
                    .append(document.get("fecha"))
                    .append("<tr><td>");
        }

        String message = messageBuilder.toString();
        return message;
    }
}
