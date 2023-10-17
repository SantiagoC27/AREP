package org.example;

import static spark.Spark.*;

public class SslExample {

    public static void main(String[] args) {
        secure("deploy/ecikeystore.p12", "1q2w3e4R", null, null);
        port(getPort());
        get("/secureHello", (req, res) -> "Hello Secure World");
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
