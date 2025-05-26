package com.cinexflix.api.service;

import com.cinexflix.api.config.youtube.YoutubeApiClient;
import com.cinexflix.api.dto.youtube.YoutubeApiResponse;
import com.cinexflix.api.dto.youtube.YoutubePlaylistItemResponse;
import com.cinexflix.api.dto.youtube.YoutubePlaylistResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import com.cinexflix.api.model.YoutubeVideo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class YoutubeService {

    private final YoutubeApiClient youtubeApiClient;
    private final ObjectMapper objectMapper;

    public YoutubeService(YoutubeApiClient youtubeApiClient, ObjectMapper objectMapper) {
        this.youtubeApiClient = youtubeApiClient;
        this.objectMapper = objectMapper;
    }

    private static final String YOUTUBE_EMBED_BASE_URL = "https://www.youtube.com/embed/";

    public YoutubeVideo getTrailer(String movieName) {
        try {
            String jsonResponse = youtubeApiClient.searchVideos(movieName + " trailer");

            YoutubeApiResponse response = objectMapper.readValue(jsonResponse, YoutubeApiResponse.class);

            if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
                YoutubeApiResponse.Item firstItem = response.getItems().get(0);

                if (firstItem != null && firstItem.getId() != null && firstItem.getId().getVideoId() != null) {
                    String videoId = firstItem.getId().getVideoId();
                    String embedUrl = YOUTUBE_EMBED_BASE_URL + videoId;
                    return new YoutubeVideo(embedUrl);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener el trailer para '" + movieName + "': " + e.getMessage());
            return new YoutubeVideo("error: " + Objects.requireNonNullElse(e.getMessage(), "Unknown API error"));
        }

        return new YoutubeVideo("No se encontró ningún trailer para: " + movieName);
    }

    // Nuevo método para obtener las playlists de un canal
    public List<YoutubePlaylistResponse.Item> getPlaylistsByChannel(String channelId) {
        try {
            String jsonResponse = youtubeApiClient.getPlaylistsByChannel(channelId);
            YoutubePlaylistResponse response = objectMapper.readValue(jsonResponse, YoutubePlaylistResponse.class);
            if (response != null && response.getItems() != null) {
                return response.getItems();
            }
        } catch (IOException e) {
            System.err.println("Error al obtener las playlists para el canal '" + channelId + "': " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Nuevo método para obtener los videos de una playlist
    public List<YoutubeVideo> getVideosFromPlaylist(String playlistId) {
        List<YoutubeVideo> youtubeVideos = new ArrayList<>();
        try {
            String jsonResponse = youtubeApiClient.getPlaylistItems(playlistId);
            YoutubePlaylistItemResponse response = objectMapper.readValue(jsonResponse,
                    YoutubePlaylistItemResponse.class);

            if (response != null && response.getItems() != null) {
                for (YoutubePlaylistItemResponse.Item item : response.getItems()) {
                    if (item != null && item.getContentDetails() != null
                            && item.getContentDetails().getVideoId() != null) {
                        String videoId = item.getContentDetails().getVideoId();
                        String embedUrl = YOUTUBE_EMBED_BASE_URL + videoId;
                        youtubeVideos.add(new YoutubeVideo(embedUrl));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener los videos de la playlist '" + playlistId + "': " + e.getMessage());
        }
        return youtubeVideos;
    }

}