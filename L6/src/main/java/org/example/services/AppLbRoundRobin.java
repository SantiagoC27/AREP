package org.example.services;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.models.Message;
import org.example.repository.MongoConnection;
import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@AllArgsConstructor
@NoArgsConstructor
public class AppLbRoundRobin {

    MongoConnection mongoConnection = new MongoConnection();
    private static final String INDEX_FILE = "index.html";
    private static final String PUBLIC_FOLDER = "public/";

    public String handleIndexRequest(Request request, Response response) {
        response.type("text/html");
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(PUBLIC_FOLDER + INDEX_FILE);

            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    StringBuilder htmlContent = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        htmlContent.append(line);
                    }
                    return htmlContent.toString();
                }
            } else {
                response.status(404);
                return "Archivo no encontrado: " + INDEX_FILE;
            }
        } catch (IOException e) {
            response.status(500);
            return "Error del servidor: " + e.getMessage();
        }
    }

    public String handleAddMessageRequest(Request request, Response response) {
        response.type("application/json");
        Message message = new Gson().fromJson(request.body(), Message.class);
        mongoConnection.insertMessage(message);
        response.status(201);
        return String.valueOf(response.status());
    }

    public String handleMessageRequest(Request request, Response response) {
        String content = mongoConnection.getData();
        return content;
    }
}
