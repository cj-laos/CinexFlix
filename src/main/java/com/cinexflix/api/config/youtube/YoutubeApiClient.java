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
        return executeGetRequest(urlString);
    }

    // Nuevo método para buscar playlists por ID de canal
    public String getPlaylistsByChannel(String channelId) throws IOException {
        String encodedChannelId = URLEncoder.encode(channelId, "UTF-8");
        // part=snippet para obtener título, descripción, etc.
        // channelId para especificar el canal
        // maxResults para limitar el número de resultados (opcional, default es 5)
        String urlString = baseUrl + "playlists?part=snippet&channelId=" + encodedChannelId + "&key=" + apiKey;
        return executeGetRequest(urlString);
    }

    // Nuevo método para obtener los videos de una playlist
    public String getPlaylistItems(String playlistId) throws IOException {
        String encodedPlaylistId = URLEncoder.encode(playlistId, "UTF-8");
        // part=snippet para obtener título, descripción, thumbnails
        // part=contentDetails para obtener el videoId
        // playlistId para especificar la playlist
        String urlString = baseUrl + "playlistItems?part=snippet,contentDetails&playlistId=" + encodedPlaylistId
                + "&key=" + apiKey;
        return executeGetRequest(urlString);
    }

    private String executeGetRequest(String urlString) throws IOException {
        URI uri = URI.create(urlString);
        URL url = uri.toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error en API de YouTube. Código: " + responseCode + ". URL: " + urlString);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

}