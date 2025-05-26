package com.cinexflix.api.controller;

import com.cinexflix.api.dto.youtube.YoutubePlaylistResponse;
import com.cinexflix.api.model.YoutubeVideo;
import com.cinexflix.api.service.YoutubeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/youtube")
@CrossOrigin(origins = "*")
public class YoutubeController {

    @Autowired
    private YoutubeService youtubeService;

    @GetMapping("/trailer")
    public YoutubeVideo getTrailer(@RequestParam("movie") String movieName) {
        return youtubeService.getTrailer(movieName);
    }

    // Nuevo endpoint para obtener playlists por ID de canal
    @GetMapping("/playlists")
    public List<YoutubePlaylistResponse.Item> getPlaylists(@RequestParam("channelId") String channelId) {
        return youtubeService.getPlaylistsByChannel(channelId);
    }

    // Nuevo endpoint para obtener videos de una playlist por ID de playlist
    @GetMapping("/playlist/videos")
    public List<YoutubeVideo> getVideosFromPlaylist(@RequestParam("playlistId") String playlistId) {
        return youtubeService.getVideosFromPlaylist(playlistId);
    }

}
