package org.example;

import static spark.Spark.*;
import java.lang.Math;
public class SparkWebServer {

    public static void main(String... args){
        port(getPort());

        get("hello", (req,res) -> "Hello Docker!");

        get("index", (req,res) -> new WebPage().getIndex());

        get("sin", (req,res) -> Math.sin(Double.parseDouble(req.queryParams("value"))));

        get("cos", (req,res) -> Math.cos(Double.parseDouble(req.queryParams("value"))));

        get("ispalindrome", (req,res) -> {
            String cadena = req.queryParams("value");
            String result = "es palindromo";
            for (int i=0; i < cadena.length(); i++) {
                if(! (cadena.charAt(i) == cadena.charAt(cadena.length()-1-i) )) {
                    result = "no es palindromo";
                }
            }
            return result;
        });

        get("vector", (req,res) -> {
            float value1 = Float.parseFloat(req.queryParams("value1"));
            float value2 = Float.parseFloat(req.queryParams("value2"));
            return Math.sqrt(Math.pow(value1, 2)+Math.pow(value2, 2));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}