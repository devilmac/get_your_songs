package com.colantoni.federico.projects.networkmodule.model.searchlyricdirect;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soap:Envelope")
@NamespaceList({@Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), @Namespace(prefix = "soap", reference = "http://www.w3.org/2003/05/soap-envelope")})
public class SearchLyricDirectResponseEnvelope {

    @Element(name = "Body")
    @Namespace(prefix = "soap")
    private ResponseBody responseBody;

    public ResponseBody getResponseBody() {

        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {

        this.responseBody = responseBody;
    }

    public static class ResponseBody {

        @Element(name = "SearchLyricDirectResponse")
        private SearchLyricDirectResponse SearchLyricResponse;

        public ResponseBody.SearchLyricDirectResponse getSearchLyricResponse() {

            return SearchLyricResponse;
        }

        public void setSearchLyricResponse(ResponseBody.SearchLyricDirectResponse searchLyricResponse) {

            SearchLyricResponse = searchLyricResponse;
        }

        @Namespace(reference = "http://api.chartlyrics.com/")
        public static class SearchLyricDirectResponse {

            @Element(name = "SearchLyricDirectResult")
            private SearchLyricDirectResult searchLyricResult;

            public SearchLyricDirectResult getSearchLyricResult() {

                return searchLyricResult;
            }

            public void setSearchLyricResult(SearchLyricDirectResult searchLyricResult) {

                this.searchLyricResult = searchLyricResult;
            }

            public static class SearchLyricDirectResult {

                @Element(name = "TrackChecksum", required = false)
                private String trackChecksum;

                @Element(name = "TrackId", required = false)
                private String trackId;

                @Element(name = "LyricChecksum", required = false)
                private String lyricChecksum;

                @Element(name = "LyricId", required = false)
                private String lyricId;

                @Element(name = "LyricSong", required = false)
                private String lyricSong;

                @Element(name = "LyricArtist", required = false)
                private String lyricArtist;

                @Element(name = "LyricUrl", required = false)
                private String lyricUrl;

                @Element(name = "LyricCovertArtUrl", required = false)
                private String lyricCovertArtUrl;

                @Element(name = "LyricRank", required = false)
                private String lyricRank;

                @Element(name = "LyricCorrectUrl", required = false)
                private String lyricCorrectUrl;

                @Element(name = "Lyric", required = false)
                private String lyric;

                public String getTrackChecksum() {

                    return trackChecksum;
                }

                public void setTrackChecksum(String trackChecksum) {

                    this.trackChecksum = trackChecksum;
                }

                public String getTrackId() {

                    return trackId;
                }

                public void setTrackId(String trackId) {

                    this.trackId = trackId;
                }

                public String getLyricChecksum() {

                    return lyricChecksum;
                }

                public void setLyricChecksum(String lyricChecksum) {

                    this.lyricChecksum = lyricChecksum;
                }

                public String getLyricId() {

                    return lyricId;
                }

                public void setLyricId(String lyricId) {

                    this.lyricId = lyricId;
                }

                public String getLyricSong() {

                    return lyricSong;
                }

                public void setLyricSong(String lyricSong) {

                    this.lyricSong = lyricSong;
                }

                public String getLyricArtist() {

                    return lyricArtist;
                }

                public void setLyricArtist(String lyricArtist) {

                    this.lyricArtist = lyricArtist;
                }

                public String getLyricUrl() {

                    return lyricUrl;
                }

                public void setLyricUrl(String lyricUrl) {

                    this.lyricUrl = lyricUrl;
                }

                public String getLyricCovertArtUrl() {

                    return lyricCovertArtUrl;
                }

                public void setLyricCovertArtUrl(String lyricCovertArtUrl) {

                    this.lyricCovertArtUrl = lyricCovertArtUrl;
                }

                public String getLyricRank() {

                    return lyricRank;
                }

                public void setLyricRank(String lyricRank) {

                    this.lyricRank = lyricRank;
                }

                public String getLyricCorrectUrl() {

                    return lyricCorrectUrl;
                }

                public void setLyricCorrectUrl(String lyricCorrectUrl) {

                    this.lyricCorrectUrl = lyricCorrectUrl;
                }

                public String getLyric() {

                    return lyric;
                }

                public void setLyric(String lyric) {

                    this.lyric = lyric;
                }
            }
        }
    }
}
