package com.colantoni.federico.projects.networkmodule.model.chartlyrics;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class SearchLyricResult {

    @Element(name = "TrackChecksum", required = false)
    private String trackChecksum;

    @Element(name = "TrackId", required = false)
    private int trackId;

    @Element(name = "LyricChecksum", required = false)
    private String lyricChecksum;

    @Element(name = "LyricId", required = false)
    private int lyricId;

    @Element(name = "SongUrl", required = false)
    private String songUrl;

    @Element(name = "ArtistUrl", required = false)
    private String artistUrl;

    @Element(name = "Artist", required = false)
    private String artist;

    @Element(name = "Song", required = false)
    private String song;

    @Element(name = "SongRank", required = false)
    private int songRank;

    public String getTrackChecksum() {

        return trackChecksum;
    }

    public void setTrackChecksum(String trackChecksum) {

        this.trackChecksum = trackChecksum;
    }

    public int getTrackId() {

        return trackId;
    }

    public void setTrackId(int trackId) {

        this.trackId = trackId;
    }

    public String getLyricChecksum() {

        return lyricChecksum;
    }

    public void setLyricChecksum(String lyricChecksum) {

        this.lyricChecksum = lyricChecksum;
    }

    public int getLyricId() {

        return lyricId;
    }

    public void setLyricId(int lyricId) {

        this.lyricId = lyricId;
    }

    public String getSongUrl() {

        return songUrl;
    }

    public void setSongUrl(String songUrl) {

        this.songUrl = songUrl;
    }

    public String getArtistUrl() {

        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {

        this.artistUrl = artistUrl;
    }

    public String getArtist() {

        return artist;
    }

    public void setArtist(String artist) {

        this.artist = artist;
    }

    public String getSong() {

        return song;
    }

    public void setSong(String song) {

        this.song = song;
    }

    public int getSongRank() {

        return songRank;
    }

    public void setSongRank(int songRank) {

        this.songRank = songRank;
    }
}
