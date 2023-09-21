package arep.Service;

import arep.Cache.MovieCache;
import arep.MovieException;
import arep.spring.Anotaciones.Componente;
import arep.spring.Anotaciones.GetMapping;
import arep.spring.Anotaciones.RequestParam;

import java.io.IOException;

@Componente
public class Services {
    static MovieCache cache = new MovieCache(15 * 60 * 1000);
    static WebService webService = new WebService(cache);
    @GetMapping(value="/movie", contentType = "application/json")
    public static byte[] getMovie (@RequestParam String name) throws IOException, MovieException {
        return webService.getMovie(name).getBytes();
    }

    @GetMapping("/file")
    public static byte[] getFile (@RequestParam String name){
        return webService.readFiles(name).getBytes();
    }

    @GetMapping(value="/img", contentType="image/jpg")
    public static byte[] getImg (@RequestParam String name) throws IOException {
        return webService.readImage(name);
    }

    @GetMapping(value="/index" , contentType = "text/html")
    public static byte[] getIndex (){
        return webService.readFiles("/index.html").getBytes();
    }

}
