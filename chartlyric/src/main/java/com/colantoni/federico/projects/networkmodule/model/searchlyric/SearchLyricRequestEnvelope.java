package com.colantoni.federico.projects.networkmodule.model.searchlyric;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Envelope")
@NamespaceList({@Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), @Namespace(prefix = "soap12", reference = "http://www.w3.org/2003/05/soap-envelope")})
public class SearchLyricRequestEnvelope {

    @Element(name = "soap12:Body")
    RequestBody requestBody;

    public SearchLyricRequestEnvelope(RequestBody requestBody) {

        this.requestBody = requestBody;
    }

    public static class RequestBody {

        @Element(name = "SearchLyric")
        SearchLyric searchLyric;

        public RequestBody(SearchLyric searchLyric) {

            this.searchLyric = searchLyric;
        }

        public static class SearchLyric {

            @Attribute(name = "xmlns")
            static final String xmlNS = "http://api.chartlyrics.com/";

            @Element(name = "artist")
            String artist;

            @Element(name = "song")
            String song;

            public SearchLyric(String artist, String song) {

                this.artist = artist;
                this.song = song;
            }
        }
    }
}
