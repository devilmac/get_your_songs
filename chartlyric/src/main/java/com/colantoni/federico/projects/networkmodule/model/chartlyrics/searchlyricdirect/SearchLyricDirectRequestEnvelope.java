package com.colantoni.federico.projects.networkmodule.model.chartlyrics.searchlyricdirect;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Envelope")
@NamespaceList({@Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), @Namespace(prefix = "soap12", reference = "http://www.w3.org/2003/05/soap-envelope")})
public class SearchLyricDirectRequestEnvelope {

    @Element(name = "soap12:Body")
    RequestBody requestBody;

    public SearchLyricDirectRequestEnvelope(RequestBody requestBody) {

        this.requestBody = requestBody;
    }

    public static class RequestBody {

        @Element(name = "SearchLyricDirect")
        SearchLyricDirect searchLyric;

        public RequestBody(SearchLyricDirect searchLyricDirect) {

            this.searchLyric = searchLyricDirect;
        }

        public static class SearchLyricDirect {

            @Attribute(name = "xmlns")
            static final String xmlNS = "http://api.chartlyrics.com/";

            @Element(name = "artist")
            String artist;

            @Element(name = "song")
            String song;

            public SearchLyricDirect(String artist, String song) {

                this.artist = artist;
                this.song = song;
            }
        }
    }
}
