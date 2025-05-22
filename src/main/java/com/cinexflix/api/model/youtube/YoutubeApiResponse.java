package com.cinexflix.api.model.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeApiResponse {
    private List<Item> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private Id id;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Id {
        @JsonProperty("videoId")
        private String videoId;
    }
}