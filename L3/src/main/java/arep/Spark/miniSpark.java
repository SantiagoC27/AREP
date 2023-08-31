package arep.Spark;

import java.util.HashMap;
import java.util.Map;

public class miniSpark {
    //1. Crear la estructura de datos
    public static Map services = new HashMap<>();
    //3. Crear un metodo de busqueda
    public static void get(String path, ServiceLambda service){
        services.put(path, service);
    }
    public static ServiceLambda search(String endpoint){
        return (ServiceLambda) services.get(endpoint);
    }

    public void start(){

    }
}
