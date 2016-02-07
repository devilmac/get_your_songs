package com.colantoni.federico.projects.networkmodule.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTrackResponseAlbumExternal_urls {

    @JsonProperty
    private String spotify;

    public String getSpotify() {return this.spotify;}

    public void setSpotify(String spotify) {this.spotify = spotify;}
}
