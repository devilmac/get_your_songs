package com.colantoni.federico.projects.networkmodule.model.searchlyric;

import com.colantoni.federico.projects.networkmodule.model.SearchLyricResult;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "soap:Envelope")
@NamespaceList({@Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), @Namespace(prefix = "soap", reference = "http://www.w3.org/2003/05/soap-envelope")})
public class SearchLyricResponseEnvelope {

    @Element(name = "Body")
    @Namespace(prefix = "soap")
    private ResponseBody responseBody;

    public ResponseBody getResponseBody() {

        return responseBody;
    }

    public static class ResponseBody {

        @Element(name = "SearchLyricResponse")
        private SearchLyricResponse SearchLyricResponse;

        public ResponseBody.SearchLyricResponse getSearchLyricResponse() {

            return SearchLyricResponse;
        }

        @Namespace(prefix = "xmlns", reference = "http://api.chartlyrics.com/")
        @Root(name = "SearchLyricResponse")
        public static class SearchLyricResponse {

            @Path("SearchLyricResult")
            @ElementList(entry = "SearchLyricResult", inline = true, required = false)
            private List<SearchLyricResult> searchLyricResult;

            @Path(value = "SearchLyricResult")
            private SearchLyricResultPath searchLyricResultPath;

            public List<SearchLyricResult> getSearchLyricResult() {

                return searchLyricResult;
            }

            public void setSearchLyricResult(List<SearchLyricResult> searchLyricResult) {

                this.searchLyricResult = searchLyricResult;
            }

            public static class SearchLyricResultPath {}
        }
    }
}
