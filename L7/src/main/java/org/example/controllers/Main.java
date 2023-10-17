package org.example.controllers;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(5001);
        staticFiles.location("/public");
        secure("deploy/ecikeystore.p12", "1q2w3e4R", null, null);

        get("/main", (request, response) -> {
            response.type("text/html");
            return "<html><head><title>Admin logueado</title></head><body><h1>Hola, Admin!</h1></body></html>";
        });
    }
}
