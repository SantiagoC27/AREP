package arep;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import arep.Cache.MovieCache;
import arep.Service.WebService;

public class Server {

    public static void main(String[] args) throws IOException, MovieException {
        System.out.println("Server is up and running. Listening on port 35000...");
        ServerSocket serverSocket = null;
        MovieCache cache = new MovieCache(15 * 60 * 1000);
        WebService webService = new WebService(cache);
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
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
                    String movieName = "";

                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received: " + inputLine);
                        if (fristLine) {
                            fristLine = false;
                            path = inputLine.split(" ")[1];
                        }
                        if (!in.ready()) {
                            break;
                        }
                    }

                    //Manjarlo como una uri, Crear una nueva clase que haga de main desde donde se van a llamar los nuevos paths y arreglar el if

                    if (path.startsWith("/movie?name=")) {
                        movieName = path.split("=")[1].split(" ")[0];
                        outputLine = respGetOK(webService.getMovie(movieName),"application/json");
                        out.write(outputLine.getBytes());
                    } else if (path.startsWith("/file")){
                        String file = path.split("/")[2];
                        outputLine = respGetOK(webService.readFiles(file),"text/plain");
                        out.write(outputLine.getBytes());
                    } else if (path.startsWith("/img")){
                        //img/ext
                        String file = path.split("/")[2];
                        String ext = file.split("\\.")[1];
                        byte[] resp = webService.readImage(file);
                        outputLine = respGetOK("", "image/" + ext);
                        out.write(outputLine.getBytes());
                        out.write(resp);
                    } else {
                        outputLine = respGetOK(webService.readFiles("/index.html"),"text/html");
                        out.write(outputLine.getBytes());
                    }

                    //out.write(outputLine.getBytes());


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
        cache.closeTimer();
    }

    public static String respGetOK(String jContent, String ContentType) {
        return "HTTP/1.1 200 OK \r\n" +
                "Access-Control-Allow-Origin: * \r\n" +
                "Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE \r\n" +
                "Access-Control-Allow-Headers: Content-Type, Authorization \r\n" +
                "Content-Type:" + ContentType + "\r\n" +
                "\r\n" +
                jContent;

    }
    public static List<byte[]> respGetOKIMG(byte[] jContent) {
        List<byte[]> headers = new ArrayList<>();
        headers.add(String.format("HTTP/1.1 200 OK \r\n").getBytes());
        headers.add("Access-Control-Allow-Origin: * \r\n".getBytes());
        headers.add("Access-Control-Allow-Methods: * \r\n".getBytes());
        headers.add("Access-Control-Allow-Headers: Content-Type, Authorization \r\n".getBytes());
        headers.add(String.format("Content-Type: image/png \r\n").getBytes());
        headers.add(jContent);

        return headers;
    }

}
