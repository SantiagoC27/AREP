package arep.Spark;

import arep.MovieException;

import java.io.IOException;

public interface ServiceLambda {
    //2. Crear un metodo de registro de la funcion
    String ejecutar(String query) throws IOException, MovieException;
}
