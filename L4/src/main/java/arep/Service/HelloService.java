package arep.Service;

import arep.Cache.MovieCache;
import arep.MovieException;
import arep.spring.Componente;
import arep.spring.GetMapping;

import java.io.IOException;

@Componente
public class HelloService {
    static MovieCache cache = new MovieCache(15 * 60 * 1000);
    static WebService webService = new WebService(cache);
    @GetMapping("/movie")
    public static byte[] GetMovie (String arg) throws IOException, MovieException {
        return webService.getMovie(arg).getBytes();
    }

    @GetMapping("/file")
    public static byte[] GetFile (String arg){
        return webService.readFiles(arg).getBytes();
    }

    @GetMapping("/img")
    public static byte[] GetImg (String arg) throws IOException {
        return webService.readImage(arg);
    }

    @GetMapping("/index")
    public static byte[] GetIndex (String arg){
        return webService.readFiles("/index.html").getBytes();
    }

}
