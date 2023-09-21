package arep.spring;

import arep.MovieException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Spring {
    private static final ComponetLoader laoder = new ComponetLoader();
    private static final HashMap<String, String[]> keysSET = new HashMap<>();
    private static final HashMap<String, String> contedTipeSET = new HashMap<>();
    private static HashMap<String, String> params = new HashMap<>();
    public static void run() throws IOException, MovieException, ClassNotFoundException {
        System.out.println("Server is up and running. Listening on port 35000...");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        laoder.cargarComponentes(new File("target/classes"));
        boolean runnig = true;
        while (runnig) {
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread(() -> {
                try {
                    OutputStream out = clientSocket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String inputLine, outputLine = "";
                    boolean fristLine = true;
                    String path = "";
                    String pathWithParams = "";
                    String method = "";

                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received: " + inputLine);
                        if (fristLine) {
                            fristLine = false;
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

                    }

                    byte[] resp = new byte[0];
                    if (method.equals("GET")) {
                        resp = laoder.ejecutar(path, params);
                    }

                    outputLine = respGetOK(laoder.getContentType(path));

                    out.write(outputLine.getBytes());
                    out.write(resp);


                    out.close();
                    in.close();
                    clientSocket.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
            t.start();
        }
        serverSocket.close();
        //cache.closeTimer();
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
