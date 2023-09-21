package arep.spring;

import arep.spring.Anotaciones.Componente;
import arep.spring.Anotaciones.GetMapping;
import arep.spring.Anotaciones.RequestParam;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class    ComponetLoader {
    private static Map<String, Method> servicios = new HashMap<>();
    public byte[] ejecutar(String endpoint, Map<String, String> queryParams) throws Exception {
        Method method =servicios.get(endpoint);
        System.out.println(endpoint);
        System.out.println(method);
        Object[] params = getParams(method, queryParams);
        if (params.length == 0) params = null;
        return (byte[]) method.invoke(null, params);
    }

    private static Object[] getParams(Method method, Map<String, String> params){
        Object[] rta = new Object[method.getParameters().length];
        for (int i = 0; i < method.getParameters().length; i++) {
            if (method.getParameters()[i].isAnnotationPresent(RequestParam.class)){
                System.out.println(method.getParameters()[i].getName());
                rta[i] = params.get(method.getParameters()[i].getName());
            }
        }
        return rta;
    }

    public String getContentType(String endpoint) {
        System.out.println(endpoint);
        return servicios.get(endpoint).getAnnotation(GetMapping.class).contentType();
    }
    public static void cargarComponentes(File carpeta) throws ClassNotFoundException {
        List<String> classNames = new ArrayList<>();
        getClassNames(carpeta, classNames);

        for (String className : classNames) {
            Class<?> clazz = Class.forName(className);

            if (clazz.isAnnotationPresent(Componente.class)) {
                Method[] methods = clazz.getDeclaredMethods();

                for (Method method : methods) {
                    if (method.isAnnotationPresent(GetMapping.class)) {
                        String ruta = method.getAnnotation(GetMapping.class).value();
                        System.out.println("Cargando método: " + method.getName());
                        servicios.put(ruta, method);
                    }
                }
            }
        }
    }

    public static void getClassNames(File carpeta, List<String> fileNames) {
        File[] archivos = carpeta.listFiles();

        if (archivos == null) {
            throw new RuntimeException();
        }

        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().endsWith(".class")) {
                String packageAndName = archivo.getPath()
                        .replace("target\\classes\\", "")
                        .replace(".class", "")
                        .replace("\\", ".");
                fileNames.add(packageAndName);
            } else if (archivo.isDirectory()) {
                getClassNames(archivo, fileNames);
            }
        }
    }
}
