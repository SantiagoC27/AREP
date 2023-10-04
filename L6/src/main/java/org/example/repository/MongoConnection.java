package org.example.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.models.Message;

import java.util.Date;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnection {

    private ConnectionString uri;
    private MongoDatabase database;

    public MongoConnection(){
        uri = new ConnectionString("mongodb+srv://cardenassantiago27:1Sv2TEQkaaznWqAQ@cluster0.hlxwhhg.mongodb.net/?retryWrites=true&w=majority&appName=AtlasApp");
        String dbname = "SparkMongodb";

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .applyConnectionString(uri).build();

        MongoClient mongoClient = null;
        try {
            mongoClient = MongoClients.create(settings);
        } catch (MongoException me){
            System.err.println("No se puede conectar con mongo: " + me);
            System.exit(1);
        }

        database = mongoClient.getDatabase(dbname);
    }
    public void insertMessage(Message message) {
        MongoCollection<Document> collection = database.getCollection("Mensajes");
        Document document = new Document()
                .append("mensaje", message.getMessage())
                .append("usuario", message.getUser())
                .append("fecha", new Date().toString());
        collection.insertOne(document);
    }

    public String getData() {
        MongoCollection<Document> collection = database.getCollection("Mensajes");
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
