package com.cinexflix.api.config.youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

public class YoutubeApiClient {

    private static YoutubeApiClient instance;

    private final String apiKey;
    private final String baseUrl;

    private YoutubeApiClient(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public static YoutubeApiClient getInstance(String apiKey, String baseUrl) {
        if (instance == null) {
            instance = new YoutubeApiClient(apiKey, baseUrl);
        }
        return instance;
    }

    public String searchVideos(String query) throws IOException {
        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        String urlString = baseUrl + "search?part=snippet&q=" + encodedQuery + "&type=video&key=" + apiKey;

        URI uri = URI.create(urlString);
        URL url = uri.toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error en API de YouTube. Código: " + responseCode);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}