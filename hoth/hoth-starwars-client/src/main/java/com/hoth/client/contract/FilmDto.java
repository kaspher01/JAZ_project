package com.hoth.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto extends GetIdClass{

    private String title;

    @JsonProperty("episode_id")
    private int episodeId;

    @JsonProperty("opening_crawl")
    private String openingCrawl;

    private String director;

    private String producer;

    @JsonProperty("release_date")
    private Date releaseDate;

    @JsonProperty("species")
    private List<String> speciesUrls;

    @JsonProperty("starships")
    private List<String> starshipsUrls;

    @JsonProperty("vehicles")
    private List<String> vehiclesUrls;

    @JsonProperty("characters")
    private List<String> charactersUrls;

    @JsonProperty("planets")
    private List<String> planetsUrls;

    private String created;
    private String edited;

}
