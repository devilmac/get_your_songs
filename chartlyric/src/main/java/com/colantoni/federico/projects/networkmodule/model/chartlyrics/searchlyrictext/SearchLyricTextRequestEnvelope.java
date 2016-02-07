package com.colantoni.federico.projects.networkmodule.model.chartlyrics.searchlyrictext;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Envelope")
@NamespaceList({@Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), @Namespace(prefix = "soap12", reference = "http://www.w3.org/2003/05/soap-envelope")})
public class SearchLyricTextRequestEnvelope {

    @Element(name = "soap12:Body")
    RequestBody requestBody;

    public SearchLyricTextRequestEnvelope(RequestBody requestBody) {

        this.requestBody = requestBody;
    }

    public static class RequestBody {

        @Element(name = "SearchLyricText")
        SearchLyricText searchLyric;

        public RequestBody(SearchLyricText searchLyric) {

            this.searchLyric = searchLyric;
        }

        public static class SearchLyricText {

            @Attribute(name = "xmlns")
            static final String xmlNS = "http://api.chartlyrics.com/";

            @Element(name = "lyricText")
            String lyricText;

            public SearchLyricText(String lyricText) {

                this.lyricText = lyricText;
            }
        }
    }
}
