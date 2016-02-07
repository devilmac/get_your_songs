package com.colantoni.federico.projects.networkmodule.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTrackResponse {

    @JsonProperty
    private GetTrackResponseExternal_urls external_urls;

    @JsonProperty
    private String[] available_markets;

    @JsonProperty
    private GetTrackResponseExternal_ids external_ids;

    @JsonProperty
    private String preview_url;

    @JsonProperty
    private GetTrackResponseAlbum album;

    @JsonProperty
    private int duration_ms;

    @JsonProperty
    private String type;

    @JsonProperty
    private String uri;

    @JsonProperty
    private int track_number;

    @JsonProperty
    private String id;

    @JsonProperty
    private GetTrackResponseArtists[] artists;

    @JsonProperty
    private int disc_number;

    @JsonProperty
    private boolean explicit;

    @JsonProperty
    private String name;

    @JsonProperty
    private String href;

    @JsonProperty
    private int popularity;

    public GetTrackResponseExternal_urls getExternal_urls() {return this.external_urls;}

    public void setExternal_urls(GetTrackResponseExternal_urls external_urls) {this.external_urls = external_urls;}

    public String[] getAvailable_markets() {return this.available_markets;}

    public void setAvailable_markets(String[] available_markets) {this.available_markets = available_markets;}

    public GetTrackResponseExternal_ids getExternal_ids() {return this.external_ids;}

    public void setExternal_ids(GetTrackResponseExternal_ids external_ids) {this.external_ids = external_ids;}

    public String getPreview_url() {return this.preview_url;}

    public void setPreview_url(String preview_url) {this.preview_url = preview_url;}

    public GetTrackResponseAlbum getAlbum() {return this.album;}

    public void setAlbum(GetTrackResponseAlbum album) {this.album = album;}

    public int getDuration_ms() {return this.duration_ms;}

    public void setDuration_ms(int duration_ms) {this.duration_ms = duration_ms;}

    public String getType() {return this.type;}

    public void setType(String type) {this.type = type;}

    public String getUri() {return this.uri;}

    public void setUri(String uri) {this.uri = uri;}

    public int getTrack_number() {return this.track_number;}

    public void setTrack_number(int track_number) {this.track_number = track_number;}

    public String getId() {return this.id;}

    public void setId(String id) {this.id = id;}

    public GetTrackResponseArtists[] getArtists() {return this.artists;}

    public void setArtists(GetTrackResponseArtists[] artists) {this.artists = artists;}

    public int getDisc_number() {return this.disc_number;}

    public void setDisc_number(int disc_number) {this.disc_number = disc_number;}

    public boolean getExplicit() {return this.explicit;}

    public void setExplicit(boolean explicit) {this.explicit = explicit;}

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public String getHref() {return this.href;}

    public void setHref(String href) {this.href = href;}

    public int getPopularity() {return this.popularity;}

    public void setPopularity(int popularity) {this.popularity = popularity;}
}
