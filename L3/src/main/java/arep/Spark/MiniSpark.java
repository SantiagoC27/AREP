package arep.Spark;

import arep.Cache.MovieCache;
import arep.Service.WebService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;

public class MiniSpark {

    private static final HashMap<String, ServiceLambda> services = new HashMap<>();
    private static final HashMap<String, String[]> keysSET = new HashMap<>();
    private static final HashMap<String, String> contedTipeSET = new HashMap<>();
    private HashMap<String, String> params = new HashMap<>();

    public void get(String metodo, String path, ServiceLambda service, String[] keys, String contedType) {
        services.put(metodo + path, service);
        keysSET.put(metodo + path, keys);
        contedTipeSET.put(metodo + path, contedType);
    }

    public ServiceLambda search(String endpoint) {
        return services.get(endpoint);
    }

    public void start() throws IOException {
        System.out.println("Server is up and running. Listening on port 35000...");
        try (ServerSocket serverSocket = new ServerSocket(35000)) {
            MovieCache cache = new MovieCache(15 * 60 * 1000);
            WebService webService = new WebService(cache, "public/");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleRequest(clientSocket)).start();
            }
        }
    }

    private void handleRequest(Socket clientSocket) {
        try (OutputStream out = clientSocket.getOutputStream();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String inputLine, outputLine = "";
            boolean firstLine = true;
            String path = "";
            String pathWithParams = "";
            String method = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    firstLine = false;
                    String[] requestParts = inputLine.split(" ");
                    method = requestParts[0];
                    pathWithParams = requestParts[1];
                    path = pathWithParams.split("\\?")[0];
                }
                if (!in.ready()) {
                    break;
                }
            }

            URL url = new URL("http://localhost:35000" + pathWithParams);
            String queryParams = url.getQuery();
            String[] parametros = new String[keysSET.size()];

            if (queryParams != null) {
                for (String param : queryParams.split("&")) {
                    String[] keyVal = param.split("=");
                    String key = keyVal[0];
                    String val = keyVal[1];
                    params.put(key, val);
                }

                if (keysSET.containsKey(method + path)) {
                    for (int i = 0; i < keysSET.get(method + path).length; i++) {
                        parametros[i] = params.get(keysSET.get(method + path)[i]);
                    }
                }
            } else {
                parametros = new String[]{};
            }

            byte[] resp = search(method + path).ejecutar(parametros);

            outputLine = respGetOK(contedTipeSET.get(method + path));

            out.write(outputLine.getBytes());
            out.write(resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String respGetOK(String contentType) {
        return "HTTP/1.1 200 OK \r\n" +
                "Access-Control-Allow-Origin: * \r\n" +
                "Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE \r\n" +
                "Access-Control-Allow-Headers: Content-Type, Authorization \r\n" +
                "Content-Type:" + contentType + "\r\n" +
                "\r\n";
    }

}
