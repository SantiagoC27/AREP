package arep;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import arep.Cache.MovieCache;
import arep.Service.WebService;
import arep.spring.ComponetLoader;

public class Server {
    public static void main(String[] args) throws IOException, MovieException {
        System.out.println("Server is up and running. Listening on port 35000...");
        ServerSocket serverSocket = null;
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

                    //out.write(respGetOK());


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

    public static byte[] respGetOK(String ContentType) {
        return ("HTTP/1.1 200 OK \r\n" +
                "Access-Control-Allow-Origin: * \r\n" +
                "Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE \r\n" +
                "Access-Control-Allow-Headers: Content-Type, Authorization \r\n" +
                "Content-Type:" + ContentType + "\r\n" +
                "\r\n").getBytes();

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
