package com.cinexflix.api.service;

import com.cinexflix.api.model.youtube.YoutubeApiResponse;
import java.net.URI;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.cinexflix.api.model.YoutubeVideo;

@Service
public class YoutubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String YOUTUBE_API_BASE_URL = "https://www.googleapis.com/youtube/v3/search";
    private static final String YOUTUBE_EMBED_BASE_URL = "https://www.youtube.com/embed/";
    private static final String API_PART_SNIPPET = "snippet";
    private static final String API_TYPE_VIDEO = "video";
    private static final String API_MAX_RESULTS = "1";
    private static final String PARAM_PART = "part";
    private static final String PARAM_QUERY = "q";
    private static final String PARAM_KEY = "key";
    private static final String PARAM_MAX_RESULTS = "maxResults";
    private static final String PARAM_TYPE = "type";

    public YoutubeVideo getTrailer(String query) {
        URI uri = UriComponentsBuilder.fromUriString(YOUTUBE_API_BASE_URL)
                .queryParam(PARAM_PART, API_PART_SNIPPET)
                .queryParam(PARAM_TYPE, API_TYPE_VIDEO)
                .queryParam(PARAM_MAX_RESULTS, API_MAX_RESULTS)
                .queryParam(PARAM_QUERY, query + " trailer")
                .queryParam(PARAM_KEY, apiKey)
                .build()
                .toUri();

        YoutubeApiResponse response = null;
        try {
            response = restTemplate.getForObject(uri, YoutubeApiResponse.class);
        } catch (Exception e) {
            System.err.println("Error al llamar a la API de YouTube para '" + query + "': " + e.getMessage());
            return new YoutubeVideo("error: " + Objects.requireNonNullElse(e.getMessage(), "Unknown API error"));
        }

        if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
            YoutubeApiResponse.Item firstItem = response.getItems().get(0);

            if (firstItem != null && firstItem.getId() != null && firstItem.getId().getVideoId() != null) {
                String videoId = firstItem.getId().getVideoId();
                String embedUrl = YOUTUBE_EMBED_BASE_URL + videoId;
                return new YoutubeVideo(embedUrl);
            }
        }

        return new YoutubeVideo("No se encontró ningún trailer: " + query);
    }
}