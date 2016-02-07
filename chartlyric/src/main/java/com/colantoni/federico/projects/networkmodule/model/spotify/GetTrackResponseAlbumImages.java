package com.colantoni.federico.projects.networkmodule.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTrackResponseAlbumImages {

    @JsonProperty
    private int height;

    @JsonProperty
    private int width;

    @JsonProperty
    private String url;

    public int getHeight() {return this.height;}

    public void setHeight(int height) {this.height = height;}

    public int getWidth() {return this.width;}

    public void setWidth(int width) {this.width = width;}

    public String getUrl() {return this.url;}

    public void setUrl(String url) {this.url = url;}
}
