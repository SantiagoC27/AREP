package org.example;

import org.example.services.AppLbRoundRobin;

import static spark.Spark.*;

public class PrincipalServer {
    public static void main(String[] args){
        AppLbRoundRobin appLbRoundRobin = new AppLbRoundRobin();
        port(getPort());
        get("index", appLbRoundRobin::handleIndexRequest);
        get("message" , appLbRoundRobin :: handleMessageRequest);
        post("message", appLbRoundRobin::handleAddMessageRequest);
    }

    private static int getPort(){
        String port = System.getenv("PORT");
        return (port != null) ? Integer.parseInt(port) : 4567;
    }
}
