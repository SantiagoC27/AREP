package arep.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ComponetLoader {
    private static Map<String, Method> servicios = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        //new String[]{"arep.Service.HelloService"}
        cargarComponentes(args);

        System.out.println(ejecutar("/hello","?name=pedro&sn=perez"));
        System.out.println(ejecutar("/hellopost","?name=pedro&sn=perez"));
    }
    public static byte[] ejecutar(String ruta, String param) throws InvocationTargetException, IllegalAccessException {
        return (byte[]) servicios.get(ruta).invoke(null, (Object) param);
    }
    public static void cargarComponentes(String[] args) throws ClassNotFoundException {
        for (String arg: args){
            Class c = Class.forName(arg);
            if (c.isAnnotationPresent(Componente.class)){
                Method[] methods = c.getDeclaredMethods();
                for (Method m: methods){
                    if (m.isAnnotationPresent(GetMapping.class)){
                        String ruta = m.getAnnotation(GetMapping.class).value();
                        System.out.println("Cargando método: " + m.getName());
                        servicios.put(ruta, m);
                    }
                }
            }
        }
    }
}
