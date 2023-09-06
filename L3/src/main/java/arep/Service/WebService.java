package arep.Service;

import arep.Cache.ICache;
import arep.MovieException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WebService {

    private final ICache cache;
    private final String path;

    public WebService(ICache cache, String path) {
        this.cache = cache;
        this.path = path;
    }

    public String getMovie(String movieName) throws IOException, MovieException {
        String cachedData = cache.getCachedMovie(movieName);
        if (cachedData != null) {
            return cachedData;
        }

        String apiKey = "a445b9e2";

        if (movieName == null || movieName.isEmpty()) {
            throw new MovieException();
        }

        URL consultURL = new URL("http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + movieName);
        HttpURLConnection connection = (HttpURLConnection) consultURL.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                String jsonResponse = response.toString();
                cache.putCache(movieName, jsonResponse);
                return jsonResponse;
            }
        } else {
            System.out.println("Request failed with response code: " + responseCode);
            return null;
        }
    }

    public String readFiles(String fileName) {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("public/" + fileName)) {
            if (inputStream != null) {
                Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);

                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
            }
        } catch (IOException ignored) {
        }

        return content.toString();
    }

    public byte[] readImage(String fileName) throws IOException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("public/" + fileName)) {
            if (inputStream != null) {
                return inputStream.readAllBytes();
            } else {
                throw new FileNotFoundException("File not found: " + fileName);
            }
        }
    }
}
