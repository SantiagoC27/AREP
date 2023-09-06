package arep;

import arep.Cache.MovieCache;
import arep.Service.WebService;
import arep.Spark.MiniSpark;

import java.io.IOException;

public class AplicacionPrueba {
    public static void main(String[] args) throws IOException {
        MiniSpark spark = new MiniSpark();
        MovieCache cache = new MovieCache(15 * 60 * 1000);
        WebService webService = new WebService(cache, "public/");
        String[] keys = {"name"};

        spark.get("GET","/movies",(arg) -> webService.getMovie(arg[0]).getBytes(), keys, "application/json");
        spark.get("GET","/files",(arg) -> webService.readFiles(arg[0]).getBytes(), keys, "text/plain");
        spark.get("GET","/img",(arg) -> webService.readImage(arg[0]), keys, "image/jpg");
        spark.get("GET","/index",(arg) -> webService.readFiles("index.html").getBytes(), keys, "text/html");
        spark.get("GET","/favicon.ico",(arg) -> webService.readFiles("favicon.ico").getBytes(), keys, "image/x-icon");
        spark.get("POST", "/post", (arg) -> "Tambien sirve el post :)".getBytes(), keys, "text/plain");
        spark.start();
    }
}
