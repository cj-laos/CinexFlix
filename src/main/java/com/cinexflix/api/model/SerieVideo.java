package com.cinexflix.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SerieVideo {
    @Id
    private String videoId;
    private String title;
    private String thumbnailUrl;
}
