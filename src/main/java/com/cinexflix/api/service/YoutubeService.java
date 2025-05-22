package com.cinexflix.api.service;

import java.util.List;
import java.net.URI;
import java.util.Map;
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

    public YoutubeVideo getTrailer(String movieName) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("https://www.googleapis.com/youtube/v3/search")
                .queryParam("part", "snippet")
                .queryParam("q", movieName + " trailer")
                .queryParam("key", apiKey)
                .queryParam("maxResults", "1")
                .queryParam("type", "video")
                .build()
                .toUri();

        Map<String, Object> response = restTemplate.getForObject(uri, Map.class);

        if (response != null && response.containsKey("items")) {
            List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
            if (!items.isEmpty()) {
                Map<String, Object> item = items.get(0);
                Map<String, Object> idMap = (Map<String, Object>) item.get("id");
                String videoId = idMap.get("videoId").toString();
                return new YoutubeVideo("https://www.youtube.com/embed/" + videoId);
            }
        }

        return new YoutubeVideo("");
    }
}