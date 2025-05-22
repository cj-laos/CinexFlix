package com.cinexflix.api.controller;

import com.cinexflix.api.model.YoutubeVideo;
import com.cinexflix.api.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/youtube")
@CrossOrigin(origins = "*") // Permite que el front acceda sin CORS, es útil considerando que el front y el
                            // back están en diferentes puertos, sino xddddd
public class YoutubeController {

    @Autowired
    private YoutubeService youtubeService;

    @GetMapping("/trailer")
    public YoutubeVideo getTrailer(@RequestParam("movie") String movieName) {
        return youtubeService.getTrailer(movieName);
    }
}
