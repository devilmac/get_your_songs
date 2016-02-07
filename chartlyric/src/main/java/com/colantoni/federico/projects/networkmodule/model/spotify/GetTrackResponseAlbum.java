package com.colantoni.federico.projects.networkmodule.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTrackResponseAlbum {

    @JsonProperty
    private String id;

    @JsonProperty
    private GetTrackResponseAlbumExternal_urls external_urls;

    @JsonProperty
    private String name;

    @JsonProperty
    private GetTrackResponseAlbumImages[] images;

    @JsonProperty
    private String album_type;

    @JsonProperty
    private String type;

    @JsonProperty
    private String href;

    @JsonProperty
    private String uri;

    public String getId() {return this.id;}

    public void setId(String id) {this.id = id;}

    public GetTrackResponseAlbumExternal_urls getExternal_urls() {return this.external_urls;}

    public void setExternal_urls(GetTrackResponseAlbumExternal_urls external_urls) {this.external_urls = external_urls;}

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public GetTrackResponseAlbumImages[] getImages() {return this.images;}

    public void setImages(GetTrackResponseAlbumImages[] images) {this.images = images;}

    public String getAlbum_type() {return this.album_type;}

    public void setAlbum_type(String album_type) {this.album_type = album_type;}

    public String getType() {return this.type;}

    public void setType(String type) {this.type = type;}

    public String getHref() {return this.href;}

    public void setHref(String href) {this.href = href;}

    public String getUri() {return this.uri;}

    public void setUri(String uri) {this.uri = uri;}
}
