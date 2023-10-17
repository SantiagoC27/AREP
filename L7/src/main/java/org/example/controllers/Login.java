package org.example.controllers;

import org.example.models.User;
import spark.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.example.services.HashGenerator.hashGenerator;
import static org.example.services.URLReader.trust;
import static spark.Spark.*;

public class Login {

    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>(Map.of(
            "admin", new User("admin", hashGenerator("admin"))
    ));

    public static void main(String[] args) {
        port(5000);
        staticFiles.location("/public");
        secure("deploy/ecikeystore.p12", "1q2w3e4R", null, null);

        post("/login", (request, response) -> {
            String username = request.queryParams("username");
            String password = request.queryParams("password");

            User user = users.get(username);
            if (user != null && user.getPassword().equals(hashGenerator(password))) {
                Session session = request.session(true);
                session.attribute("username", username);
                trust("deploy/myTrustStore", "1q2w3e4R");
                response.redirect("https://localhost:5001/main");
                return null;
            }

            return "ERROR";
        });
    }
}



