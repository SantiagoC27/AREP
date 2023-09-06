package arep.Spark;

import arep.MovieException;

import java.io.IOException;

public interface ServiceLambda {
    byte[] ejecutar(String[] args) throws Exception;
}
