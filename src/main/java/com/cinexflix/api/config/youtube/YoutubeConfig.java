package com.cinexflix.api.config.youtube;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class YoutubeConfig {

    @Value("${youtube.api.key}")
    private String apiKey;

    @Value("${youtube.api.base-url}")
    private String baseUrl;

    @Bean
    public YoutubeApiClient youtubeApiClient() {
        return YoutubeApiClient.getInstance(apiKey, baseUrl);
    }
}
